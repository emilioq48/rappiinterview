# Rappi Interview
Interview for Rappi

1. Las capas de la aplicación (por ejemplo capa de persistencia, vistas, red, negocio, etc) y qué clases pertenecen a cual.
2. La responsabilidad de cada clase creada.

Capas de la aplicación y explicación de las clases dentro de ellas

***Dominio***

Contiene la clase Item que es la clase modelo del principal objeto que manejamos en la aplicación.

***Intraestructura***

Contiene todas las clases relacionadas con llamados a servicios y repositorios

--- Managers ---

Clases:
ManagersModule: Módulo de Dagger para satisfacier las dependencias de ImageManager, MoviesManager y MoviesRepository. Es la encargada de saber cómo crear las intancias de las clases que implementas dichas interfaces. Esta lógica es la misma para los próximos XModules.

ImageManagerImpl: Implementación de la interfaz ImageManager. Permite cargar una imagen utilizando la librería Glide

MoviesManagerImpl: Implementación de la interfaz MoviesManager. Permite llamar a los diferentes métodos de MoviesService para realizar los diferentes tipos de llamados a servicios que tengan que ver con movies en la app.

RetrofitManagerImpl:  Implementación de la interfaz RetrofitManager. Permite obtener una instancia de Retrofit, útil para luego poder proveer el servicio de movies (MoviesService).

ImageManager,MoviesManager,RetrofitManager: Interfaces que definen los métodos necesarios para manejar imágenes, movies y retrofit. Luego sus respectivas implementaciones definirán cómo se hará esto en cada método ya definido por cada una de ellas.

--- Networking---

Contiene los tipos de dato de respuestas directas de los servicios

MoviesServiceResponse: Tipo de dato necesario para mapear la respuesta del servicio que retorna todas las movies
SelectedMoviesServiceResponse: Tipo de dato necesario para mapear la respuesta del servicio que retorna todas movies "upcoming", "top_rated", "popular" y la del servicio de search.

--- Repository ---

Contiene las clases relacionads con repositorios locales

MoviesRepositoryImpl: Implementación de la interfaz MoviesRepository. Permite guardar movies, obtener todas las movies, filtrar por movies y buscar movies online.
MoviesRepository: Interfaz que define los métodos para acceder al repositorio

MoviesService: Interfaz que usa Retrofit para crear su instancia. Define las llamadas a los servicios que se van a usar, de que tipo son (GET/POST), sus parámetros, etc.

NetModule: Módulo de dagger para proveer RetrofitManager, MoviesService y Gson.

RestConstants: Un objecto que contiene constantes relacionadas con nuestro servicio REST.

MovieCategoryUtils: Un Utils para manejar las categorías de los Items.

***Presentación***

Esta capa maneja todo lo relacionado con elementos visuales y altamente ligados con la tecnología (en este caso el framework de Android)

Main MVP
MainActivity: La activity principal de la aplicación, implementa MainView
MainPresenter: El presenter del Main MVP. Se encarga de acceder a los repositorios (que en este caso serían los models) y llamar a los servicios y luego llamar a la view para mostrar los resultados.

MainContract: Define el contrato entre la view y el presenter

MainModule: Módulo de Dagger para proveer todo lo relacionado con el MVP. En este caso además MainActivity implementa MoviesAdapter.MovieClickListener para especificar qué hacer cuando se clickea un item perteneciente al adapter.

MoviesAdapter: Implementación de RecyclerView.Adapter para poder manipular los items que deseamos que muestre nuestra recyclerview en MainActivity. Contiene una clase MovieViewHolder que implementa la clase RecyclerView.ViewHolder y se encarga de bindear los datos del Item en la posición actual con la view correspondiente a ese elemento. Esta clase también implementa  LayoutContainer para poder utilizar android kotlin extensions dentro del ViewHolder.

RappiInterviewGlideModule: Módulo de dagger para proveer la instancia de glide.

UtilsModule: Módulo de dagger para proveer la instancias de los útils PopUpsUtils, MovieCategoryUtils.

PopUpsUtils: Clase para mostrar los diálogos de error general y del detalle de las movies.

--------------

Extensions.kt: Es un archivo kotlin que contiene extension functions. En este caso sólo contenemos una extesion function que permite ocultar el teclado desde cualquier Activity.

MainPresenterTest: Clase que contiene algunos tests relacionados con MainPresenter.

**********************************************************************************************

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
El principio de responsabilidad única es el primero de los principios de los principios SOLID (S = Single-Responsability) y su propósito es la modularización sea lo más atómica posible. Es decir que cada clase o método tenga un y sólo un propósito.
2. Qué características tiene, según su opinión, un “buen” código o código limpio?
* Bien modularizado
* Consistente en el sentido de la nomenclatura de las variables/constantes/atributos xml/resources/file names
* Que las clases que busquen resolver problemas conocidos sigan patrones de diseno diseño exitosos en resolver dichos problemas.
* Que exista una arquitectura para el proyecto
* Que sea simple y poco costoso realizar un cambio o agregar un nuevo feature. Mantenibilidad + Extensión
* Que siga los principios SOLID






