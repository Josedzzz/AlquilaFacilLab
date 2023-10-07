package co.edu.uniquindio.model;

import co.edu.uniquindio.exceptions.*;
import co.edu.uniquindio.utilities.ArchivoUtils;
import co.edu.uniquindio.utilities.Propiedades;
import lombok.Getter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Empresa {

    @Getter
    private ArrayList<Cliente> listaClientes;
    @Getter
    private ArrayList<Vehiculo> listaVehiculos;
    @Getter
    private ArrayList<Registro> listaRegistros;
    private static final Logger LOGGER = Logger.getLogger(Empresa.class.getName());
    private final Propiedades propiedades = Propiedades.getInstance();

    //Variable que tendra la instancia de esta clase
    private static Empresa empresa;

    /**
     * Constructor que debe de ser privado para que ninguna otra clase pueda crear instancias de esta clase
     */
    private Empresa() {
        try {
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter( new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log( Level.SEVERE, e.getMessage() );
        }
        LOGGER.log(Level.INFO, "Se crea una nueva instancia de la empresa");
        this.listaClientes = new ArrayList<Cliente>();
        this.listaVehiculos = new ArrayList<Vehiculo>();
        this.listaRegistros = new ArrayList<Registro>();
        //Se cargan las listas serializadas de la empresa
        try {
            listaClientes = (ArrayList<Cliente>) ArchivoUtils.deserializarObjeto("src/main/resources/info/clientes.data");
            LOGGER.log(Level.INFO, "Se carga la lista de clientes serializada");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "No se pudo cargar la lista de clientes serializadas: " + e.getMessage());
        }
        try {
            listaVehiculos = (ArrayList<Vehiculo>) ArchivoUtils.deserializarObjeto("src/main/resources/info/vehiculos.data");
            LOGGER.log(Level.INFO, "Se carga la lista de vehiculos serializada");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "No se pudo cargar la lista de vehiculos serializadas: " + e.getMessage());
        }
        try {
            listaRegistros = (ArrayList<Registro>) ArchivoUtils.deserializarObjeto("src/main/resources/info/registros.data");
            LOGGER.log(Level.INFO, "Se carga la lista de registros serializada");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "No se pudo cargar la lista de vehiculos serializados: " + e.getMessage());

        }

        //SE QUEMAN DATOS
       /* Cliente cliente = new Cliente("111", "Jose", "3214567890", "jose@prueba.com", "Armenia", "Cra 7 #45-67");
        Cliente cliente1 = new Cliente("222","Camilo","3157024987","camilo@prueba.com","Quimbaya","Cra 5 #25-59");
        Cliente cliente2 = new Cliente("333","Juan","3103784281","juan@prueba.com","Montenegro","Cra 10 #10-38");
        listaClientes.add(cliente);
        listaClientes.add(cliente1);
        listaClientes.add(cliente2);*/

        Vehiculo vehiculo = new Vehiculo("123-ABC","a36","1",MarcaVehiculo.BMW,"2000","src/main/resources/images/bmw.jpg",60000,200000,TipoCajaVehiculo.MANUAL,4);
        Vehiculo vehiculo1 = new Vehiculo("456-DEF","Model 3","2",MarcaVehiculo.TESLA,"2022","src/main/resources/images/tesla.jpg",15000,250000,TipoCajaVehiculo.AUTOMATICO,4);
        Vehiculo vehiculo2 = new Vehiculo("789-GHI", "Rx-7","3",MarcaVehiculo.MAZDA,"2006","src/main/resources/images/mazda.jpg",120000,180000,TipoCajaVehiculo.MANUAL,2);
        listaVehiculos.add(vehiculo);
        listaVehiculos.add(vehiculo1);
        listaVehiculos.add(vehiculo2);
    }

    /**
     * Metodo que se usara en otras clases que requieran la instancia de empresa
     * @return Instancia de la empresa
     */
    public static Empresa getInstance() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    //FUNCIONES DE EMPRESA=======================================================================================

    //CRUD CLIENTE===============================================================================================

    /**
     * Obtiene el cliente dada la cedula
     * @param cedula
     * @return
     */

    public Cliente obtenerCliente(String cedula){
        Cliente clienteEncontrado = null;
        for(Cliente cliente : listaClientes){
            if(cliente.getCedula().equals(cedula)){
                clienteEncontrado = cliente;
            }
        }
        return clienteEncontrado;
    }

    /**
     * Retorna un valo booleano si se pudo crear el cliente
     * @param cedula
     * @param nombre
     * @param telefono
     * @param email
     * @param ciudad
     * @param direccion
     * @return
     * @throws ClienteYaExistenteException
     * @throws AtributosVaciosException
     */

    //Validar atributos vacios cambiar el boolean y convertir a cliente retornando el objeto cliente

    public Cliente crearCliente(String cedula, String nombre, String telefono, String email, String ciudad, String direccion) throws AtributosVaciosException, CLienteYaExistenteException {

        Cliente clienteEncontrado = obtenerCliente(cedula);

        if(cedula == null || cedula.isBlank()){
            LOGGER.log(Level.WARNING, "La cedula es obligatoria para el registro" );
            //throw new AtributosVaciosException("La cedula es obligatoria");
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteCedula"));
        }

        if(nombre == null || nombre.isBlank()){
            LOGGER.log(Level.WARNING, "El nombre es obligatorio para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteNombre"));
        }

        if(telefono == null || telefono.isBlank()){
            LOGGER.log(Level.WARNING, "El telefono es obligatorio para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearCLienteTelefono"));
        }

        if(email == null || email.isBlank()){
            LOGGER.log(Level.WARNING, "El email es obligatorio para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteEmail"));
        }

        if(ciudad == null || ciudad.isBlank()){
            LOGGER.log(Level.WARNING, "La ciudad es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteCiudad"));
        }

        if(direccion == null || direccion.isBlank()){
            LOGGER.log(Level.WARNING, "La direccion es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteDireccion"));
        }

        if(clienteEncontrado != null){
            LOGGER.log(Level.SEVERE, "La cedula " + cedula + " ya esta registrada" );
            throw new CLienteYaExistenteException(propiedades.getResourceBundle().getString("crearClienteExiste"));
        }

        Cliente clienteNuevo = Cliente.builder()
                .cedula(cedula)
                .nombre(nombre)
                .telefono(telefono)
                .email(email)
                .ciudad(ciudad)
                .direccion(direccion)
                .build();


        listaClientes.add(clienteNuevo);
        LOGGER.log(Level.INFO, "Se ha registrado un nuevo cliente con cedula: " + cedula + "");
        //Serializo el objeto
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/info/clientes.data", listaClientes);
            LOGGER.log(Level.INFO, "Serializo el nuevo cliente");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "No se pudo serializar el nuevo cliente: " + e.getMessage());
        }

        return clienteNuevo;
    }

    /**
     * Actualiza los datos del cliente
     * @param cedula
     * @param nombre
     * @param telefono
     * @param email
     * @param ciudad
     * @param direccion
     * @throw ClienteNoRegistradoException
     */

    public void actualizarCliente(String cedula, String nombre, String telefono, String email, String ciudad, String direccion)throws AtributosVaciosException, ClienteNoRegistradoException{

        Cliente clienteEncontrado = obtenerCliente(cedula);

        if(cedula == null || cedula.isBlank()){
            LOGGER.log(Level.WARNING, "La cedula es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteCedula"));
        }

        if(nombre == null || nombre.isBlank()){
            LOGGER.log(Level.WARNING, "El nombre es obligatorio para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteNombre"));
        }

        if(telefono == null || telefono.isBlank()){
            LOGGER.log(Level.WARNING, "El telefono es obligatorio para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearCLienteTelefono"));
        }

        if(email == null || email.isBlank()){
            LOGGER.log(Level.WARNING, "El email es obligatorio para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteEmail"));
        }

        if(ciudad == null || ciudad.isBlank()){
            LOGGER.log(Level.WARNING, "La ciudad es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteCiudad"));
        }

        if(direccion == null || direccion.isBlank()){
            LOGGER.log(Level.WARNING, "La direccion es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearClienteDireccion"));
        }

        if(clienteEncontrado == null){
            LOGGER.log(Level.SEVERE, "La cedula " + cedula + " no esta registrada" );
            throw new ClienteNoRegistradoException(propiedades.getResourceBundle().getString("actualizarClienteNoExiste"));
        }
        clienteEncontrado.setNombre(nombre);
        clienteEncontrado.setTelefono(telefono);
        clienteEncontrado.setCiudad(ciudad);
        clienteEncontrado.setDireccion(direccion);
        LOGGER.log(Level.INFO, "Se actualizaron los datos del cliente: " + cedula);
        //Serializo el objeto
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/info/clientes.data", listaClientes);
            LOGGER.log(Level.INFO, "Serializo los datos actualizados del cliente");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "No se pudo serializar los datos actualizados del cliente: " + e.getMessage());
        }
    }

    /**
     * Elimina un cliente dado su cedula
     * @param cedula
     * @throw ClienteNoRegistradoException
     */

    public void eliminarCliente(String cedula)throws ClienteNoRegistradoException {
        Cliente clientePorEliminar = obtenerCliente(cedula);
        if(clientePorEliminar != null){
            listaClientes.remove(clientePorEliminar);
            LOGGER.log(Level.INFO, "Se elimino el cliente");
            //Serializo el objeto
            try {
                ArchivoUtils.serializarObjeto("src/main/resources/info/clientes.data", listaClientes);
                LOGGER.log(Level.INFO, "Serializo el cliente eliminado");
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "No se pudo serializar el cliente eliminado: " + e.getMessage());
            }
        }else{
            LOGGER.log(Level.SEVERE, "La cedula " + cedula + " no esta registrada" );
            throw new ClienteNoRegistradoException(propiedades.getResourceBundle().getString("actualizarClienteNoExiste"));
        }
    }

    //CRUD VEHICULO==========================================================================================

    /**
     * Obtiene el vehiculo dada la placa
     * @param placa
     * @return
     */

    public Vehiculo obtenerVehiculo(String placa){
        Vehiculo vehiculoEncontrado = null;
        for(Vehiculo vehiculo : listaVehiculos){
            if(vehiculo.getPlaca().equals(placa)){
                vehiculoEncontrado = vehiculo;
            }
        }
        return vehiculoEncontrado;
    }

    /**
     * Retorna un valo booleano si se pudo crear el vehiculo
     * @param placa
     * @param referencia
     * @param marcaVehiculo
     * @param modelo
     * @param rutaImagenes
     * @param kilometraje
     * @param precioAlquiler
     * @param tipoCajaVehiculo
     * @param numSillas
     * @return
     * @throws VehiculoYaExistenteException
     */
    public Vehiculo crearVehiculo(String placa,String referencia, MarcaVehiculo marcaVehiculo,String modelo, String imagenVehiculo, double kilometraje, double precioAlquiler, TipoCajaVehiculo tipoCajaVehiculo, int numSillas) throws AtributosVaciosException, AtributoNegativoException, VehiculoYaExistenteException {

        Vehiculo vehiculoAsociado = obtenerVehiculo(placa);

        if(placa == "" || placa.isEmpty()){
            LOGGER.log(Level.WARNING, "La placa es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearVehiculoPlaca"));
        }

        if(referencia == "" || referencia.isEmpty()){
            LOGGER.log(Level.WARNING, "La referencia es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearVehiculoReferencia"));
        }

        if(marcaVehiculo == null){
            LOGGER.log(Level.WARNING, "La marca es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearVehiculoMarca"));
        }

        if(modelo == "" || modelo.isEmpty()){
            LOGGER.log(Level.WARNING, "El modelo es obligatorio para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearVehiculoModelo"));
        }

        if(imagenVehiculo == null || imagenVehiculo == ""){
            LOGGER.log(Level.WARNING, "La imagen es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearVehiculoImagen"));
        }

        if(kilometraje < 0){
            LOGGER.log(Level.WARNING, "El kilometraje debe ser mayor a 0" );
            throw new AtributoNegativoException(propiedades.getResourceBundle().getString("crearVehiculoKilometraje"));
        }

        if(precioAlquiler < 0){
            LOGGER.log(Level.WARNING, "El precio debe ser mayor a 0" );
            throw new AtributoNegativoException(propiedades.getResourceBundle().getString("crearVehiculoPrecio"));
        }

        if(tipoCajaVehiculo == null){
            LOGGER.log(Level.WARNING, "La caja del vehiculo es obligatoria para el registro" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearVehiculoCaja"));
        }

        if(numSillas < 0){
            LOGGER.log(Level.WARNING, "El numero de sillas debe ser mayor a 0" );
            throw new AtributoNegativoException(propiedades.getResourceBundle().getString("crearVehiculoSillas"));
        }

        if(vehiculoAsociado != null){
            LOGGER.log(Level.SEVERE, "La placa " + placa + " ya esta registrada" );
            throw new VehiculoYaExistenteException(propiedades.getResourceBundle().getString("crearVehiculoExiste"));
        }

        Vehiculo vehiculoNuevo = Vehiculo.builder()
                .placa(placa)
                .referencia(referencia)
                .marcaVehiculo(marcaVehiculo)
                .modelo(modelo)
                .imagenVehiculo(imagenVehiculo)
                .kilometraje(kilometraje)
                .precioAlquiler(precioAlquiler)
                .tipoCajaVehiculo(tipoCajaVehiculo)
                .numSillas(numSillas)
                .build();

        listaVehiculos.add(vehiculoNuevo);
        LOGGER.log(Level.INFO, "Se ha registrado un nuevo vehiculo con placa: " + placa + "");
        //Serializo el objeto
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/info/vehiculos.data", listaVehiculos);
            LOGGER.log(Level.INFO, "Serializo el nuevo vehiculo");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "No se pudo serializar el nuevo vehiculo: " + e.getMessage());
        }
        return vehiculoNuevo;
    }



    /**
     * Actualiza algunos de los datos de el vehiculo segun la placa
     * @param placa
     * @param kilometraje
     * @param precioAlquiler
     * @param tipoCajaVehiculo
     * @throws VehiculoNoRegistradoException
     */

    public void actualizarVehiculo(String placa, double kilometraje,double precioAlquiler, TipoCajaVehiculo tipoCajaVehiculo)throws AtributosVaciosException, AtributoNegativoException, VehiculoNoRegistradoException{

        Vehiculo vehiculoEncontrado = obtenerVehiculo(placa);

        if(kilometraje < 0){
            LOGGER.log(Level.WARNING, "El kilometraje debe ser mayor a 0" );
            throw new AtributoNegativoException(propiedades.getResourceBundle().getString("crearVehiculoKilometraje"));
        }

        if(precioAlquiler < 0){
            LOGGER.log(Level.WARNING, "El precio debe ser mayor a 0" );
            throw new AtributoNegativoException(propiedades.getResourceBundle().getString("crearVehiculoPrecio"));
        }

        if(tipoCajaVehiculo == null){
            LOGGER.log(Level.WARNING, "La caja del vehiculo es obligatoria para podre actualizar" );
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearVehiculoCaja"));
        }

        if(vehiculoEncontrado == null){
            LOGGER.log(Level.SEVERE, "La placa " + placa + " no esta registrada" );
            throw new VehiculoNoRegistradoException(propiedades.getResourceBundle().getString("actualizarVehiculo"));
        }
        vehiculoEncontrado.setKilometraje(kilometraje);
        vehiculoEncontrado.setPrecioAlquiler(precioAlquiler);
        vehiculoEncontrado.setTipoCajaVehiculo(tipoCajaVehiculo);
        LOGGER.log(Level.INFO, "Los datos del vehículo " + placa + " fueron actualizados");
        //Serializo el objeto
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/info/vehiculos.data", listaVehiculos);
            LOGGER.log(Level.INFO, "Serializo el vehiculo actualizado");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "No se pudo serializar vehiculo actualizado: " + e.getMessage());
        }
    }


    /**
     * Elimina un vehiculo dada su placa
     * @param placa
     * @throws VehiculoNoRegistradoException
     */

    public void eliminarVehiculo(String placa) throws VehiculoNoRegistradoException{
        Vehiculo vehiculoPorEliminar = obtenerVehiculo(placa);
        if(vehiculoPorEliminar != null){
            listaVehiculos.remove(vehiculoPorEliminar);
            LOGGER.log(Level.INFO, "El vehiculo fue eliminado");
            //Serializo el objeto
            try {
                ArchivoUtils.serializarObjeto("src/main/resources/info/vehiculos.data", listaVehiculos);
                LOGGER.log(Level.INFO, "Serializo el vehiculo eliminado");
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "No se pudo serializar el vehiculo eliminado: " + e.getMessage());
            }
        }else{
            LOGGER.log(Level.SEVERE, "La placa " + placa + " no esta registrada" );
            throw new VehiculoNoRegistradoException(propiedades.getResourceBundle().getString("actualizarVehiculo"));
        }
    }

    //CRUD REGISTRO================================================================================================


    /**
     * Crea un registro para alquilar un vehiculo
     * @param cedulaCliente cedula del cliente responsable
     * @param vehiculo vehiculo a alquilar
     * @param fechaInicial inicio del alquiler
     * @param fechaFinal final del alquiler
     * @return el registro creado
     * @throws AtributosVaciosException
     * @throws FechaInvalidaException
     * @throws ClienteNoRegistradoException
     */
    public Registro crearRegistro(String cedulaCliente, Vehiculo vehiculo, LocalDate fechaInicial, LocalDate fechaFinal) throws AtributosVaciosException, FechaInvalidaException, ClienteNoRegistradoException {
        if (cedulaCliente == null || cedulaCliente.equals("")) {
            LOGGER.log(Level.WARNING, "La cédula del cliente es un campo obligatorio");
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearRegistroCedulaCliente"));
        }
        if (vehiculo == null) {
            LOGGER.log(Level.WARNING, "El vehiculo es un campo obligatorio");
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearRegistroVehiculo"));
        }
        if (fechaInicial == null) {
            LOGGER.log(Level.WARNING, "La fecha de inicio es un campo obligatorio");
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearRegistroFechaInicial"));
        }
        if (fechaFinal == null) {
            LOGGER.log(Level.WARNING, "La fecha final es un campo obligatorio");
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("crearRegistroFechaFinal"));
        }
        if (!fechaInicial.isBefore(fechaFinal)) {
            LOGGER.log(Level.WARNING, "La fecha inicial no puede ser mayor a la fecha final");
            throw new FechaInvalidaException(propiedades.getResourceBundle().getString("crearRegistroFechaError"));
        }
        //Calcula los dias
        long dias = fechaInicial.until(fechaFinal, ChronoUnit.DAYS);
        Cliente cliente = obtenerCliente(cedulaCliente);
        if (cliente == null) {
            LOGGER.log(Level.WARNING, "La fecha inicial no puede ser mayor a la fecha final");
            throw new ClienteNoRegistradoException(propiedades.getResourceBundle().getString("crearRegistroClienteError"));
        }
        //Calculo el precio para la factura
        double precio = vehiculo.getPrecioAlquiler() * dias;
        //Creo el registro
        Registro registro = Registro.builder()
                .cliente(cliente)
                .vehiculo(vehiculo)
                .fechaRegistro(LocalDateTime.now())
                .fechaInicio(fechaInicial)
                .fechaRegreso(fechaFinal)
                .precioFactura(precio)
                .build();
        listaRegistros.add(registro);
        LOGGER.log(Level.INFO, "Un registro fue creado de manera exitosa");
        //Serializo el objeto
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/info/registros.data", listaRegistros);
            LOGGER.log(Level.INFO, "Serializo el nuevo registro");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "No se pudo serializar el nuevo registro: " + e.getMessage());
        }

        return registro;
    }

    /**
     * Obtiene los vehiculos que estan dentro de las fechas especificadas
     * @param fechaInicial Fecha inicial a revisar
     * @param fechaFinal Fecha final a revisar
     * @return Lista de vehiculos disponibles
     */
    public ArrayList<Vehiculo> obtenerVehiculosDisponibles(LocalDate fechaInicial, LocalDate fechaFinal) {
        ArrayList<Vehiculo> vehiculosDisponibles = listaVehiculos;
        for (Registro registro : listaRegistros) {
            // Verifica si las fechas se solapan, si es así, el vehículo no está disponible
            if (!(fechaFinal.isBefore(registro.getFechaInicio()) || fechaInicial.isAfter(registro.getFechaRegreso()))) {
                vehiculosDisponibles.remove(registro.getVehiculo());
            }
        }
        // Ordena los vehículos por precio de alquiler de menor a mayor
        Collections.sort(vehiculosDisponibles, Comparator.comparingDouble(Vehiculo::getPrecioAlquiler));
        return vehiculosDisponibles;
    }

    /**
     * Valida que las fechas esten ingresadas de manera correcta
     * @param fechaInicial Fecha inicial a validar
     * @param fechaFinal Fecha final a validar
     * @throws FechaInvalidaException
     */
    public void validarFechas(LocalDate fechaInicial, LocalDate fechaFinal) throws FechaInvalidaException, AtributosVaciosException {
        if (fechaInicial == null) {
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("validarFechasFechaInicial"));
        }
        if (fechaFinal == null) {
            throw new AtributosVaciosException(propiedades.getResourceBundle().getString("validarFechasFechaFinal"));
        }
        if (!fechaInicial.isBefore(fechaFinal)) {
            throw new FechaInvalidaException(propiedades.getResourceBundle().getString("validarFechasFechaError"));
        }
    }


    //FUNCIONES PARA DATOS DE LA EMPRESA ============================================================================

    /**
     * Lista de vehiculos alquilados
     * @param fechaInicial fecha inical a revisar
     * @param fechaFinal fecha final a revisar
     * @return lista de vehiculos alquilados entre la fechas que pasan como parametro
     */
    public ArrayList<Vehiculo> obtenerVehiculosAlquilados(LocalDate fechaInicial, LocalDate fechaFinal) {
        ArrayList<Vehiculo> vehiculosAlquilados = new ArrayList<>();
        for (Registro registro : listaRegistros) {
            // Verifica si las fechas se solapan, si es así, el vehículo no está disponible
            if (!(fechaFinal.isBefore(registro.getFechaInicio()) || fechaInicial.isAfter(registro.getFechaRegreso()))) {
                vehiculosAlquilados.add(registro.getVehiculo());
            }
        }
        return vehiculosAlquilados;
    }

    /**
     * Calcula el total ganado por registros entre una fecha inicial y una final
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public double calcularTotalGanado(LocalDate fechaInicial, LocalDate fechaFinal) {
        double total = 0.0;
        for (Registro registro : listaRegistros) {
            // Verifica si las fechas se solapan, si es así, el vehículo no está disponible
            if (!(fechaFinal.isBefore(registro.getFechaInicio()) || fechaInicial.isAfter(registro.getFechaRegreso()))) {
                total += registro.getPrecioFactura();
            }
        }
        return total;
    }

    /**
     * Lista de registros dentro de las fechas
     * @param fechaIncial
     * @param fechaFinal
     * @return
     */
    public ArrayList<Registro> obtenerRegistrosEnFechas(LocalDate fechaIncial, LocalDate fechaFinal) {
        ArrayList<Registro> registrosEnFechas = new ArrayList<>();
        for (Registro registro : listaRegistros) {
            // Verifica si las fechas se solapan, si es así, el vehículo no está disponible
            if (!(fechaFinal.isBefore(registro.getFechaInicio()) || fechaIncial.isAfter(registro.getFechaRegreso()))) {
                registrosEnFechas.add(registro);
            }
        }
        return registrosEnFechas;
    }

    /**
     * Encuentra la marca que mas se alquila revisando toda la lista de registros
     * @return
     */
    public MarcaVehiculo encontrarMarcaMasAlquilada() throws ListaVaciaException {
        if (listaRegistros == null || listaRegistros.isEmpty()) {
            throw new ListaVaciaException(propiedades.getResourceBundle().getString("encontrarMarcaMasAlquiladaListaVacia"));
        }
        int[] conteoMarcas = new int[MarcaVehiculo.values().length];
        // Inicializa el conteo de cada marca en 0
        for (Registro registro : listaRegistros) {
            MarcaVehiculo marca = registro.getVehiculo().getMarcaVehiculo();
            conteoMarcas[marca.ordinal()]++;
        }
        // Encuentra la marca con el conteo más alto
        MarcaVehiculo marcaMasAlquilada = null;
        int maxConteo = 0;
        for (int i = 0; i < conteoMarcas.length; i++) {
            if (conteoMarcas[i] > maxConteo) {
                maxConteo = conteoMarcas[i];
                marcaMasAlquilada = MarcaVehiculo.values()[i];
            }
        }
        return marcaMasAlquilada;
    }



}
