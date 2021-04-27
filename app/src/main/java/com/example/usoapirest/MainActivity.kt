package com.example.usoapirest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), android.widget.SearchView.OnQueryTextListener {

    lateinit var list_employee: Employee
    lateinit var employeeAdapter: EmployeeAdapter
    lateinit var recycler_employees:RecyclerView
    lateinit var searchBreed:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBreed = findViewById(R.id.searchBreed)
        searchBreed.setOnQueryTextListener(this)
        recycler_employees = findViewById(R.id.recycler_employees)
    }

    private fun initCharacter(employees: EmployeeResponse) {
        if(employees.status == "success"){
            list_employee = employees.data
        }
        employeeAdapter = EmployeeAdapter(listOf(list_employee))
        recycler_employees.setHasFixedSize(true)
        recycler_employees.layoutManager = LinearLayoutManager(this)
        recycler_employees.adapter = employeeAdapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/public/api/v1/employee/") //tiene que terminar con una barra
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    override fun onQueryTextSubmit(query: String): Boolean {        //estos metodos se implementan del OnQueryTextListener
        searchByName(query!!.toLowerCase())             //Metodo paralanzar la petición cuando el usuario termina de escribir en el buscador, por lo que modificaremos la función para llamar a searchByName().
        System.out.println("*************OK onQueryTextSubmit**************")
        return true
    }

    private fun searchByName(query: String) {           //Recordar que las peticion a BD o que requieren internet deben hacerse por Hilos, Para eso usamos ANKO
        System.out.println("*********SearchByName***********")

        doAsync {
            //LINEA 56 esta fallando.

            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query").execute()
            val employees = call.body() as EmployeeResponse       //Body es para obtener solo el "cuerpo" o la info que nesecitamos nada mas. Se devuelve un objeto generico por lo que hay que hacer cast
            System.out.println("************PRUEBA**")
            uiThread {
                employees
                if(employees.status == "success") {
                    initCharacter(employees)      //Si la resp es succes, inicia la vista, o echa a andar el Recycler con los datos que recibimos y setea el adapter, algo asi
                }else{
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }.show()
    }

    override fun onQueryTextChange(newText: String?): Boolean {     //estos metodos se implementan del OnQueryTextListener
        return true
    }

    /*private fun hideKeyboard(){
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(viewRoot.windowToken, 0)
    } */
}