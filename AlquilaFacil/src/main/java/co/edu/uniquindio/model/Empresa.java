package co.edu.uniquindio.model;

import co.edu.uniquindio.exceptions.*;
import lombok.Getter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
        Cliente cliente = new Cliente("111", "Jose", "111", "111", "Armenia", "111");
        listaClientes.add(cliente);
        this.listaVehiculos = new ArrayList<Vehiculo>();
        this.listaRegistros = new ArrayList<Registro>();
    }

    /**
     * Metodo que se usara en otras clases que requieran la instancia de empresa
     * @return Instancia de la empresa
     */
    public static Empresa getInstance() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        LOGGER.log(Level.INFO, "Se ha instanciado empresa");
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
            throw new AtributosVaciosException("La cedula es obligatoria");
        }

        if(nombre == null || nombre.isBlank()){
            LOGGER.log(Level.WARNING, "El nombre es obligatorio para el registro" );
            throw new AtributosVaciosException("El nombre es obligatorio");
        }

        if(telefono == null || telefono.isBlank()){
            LOGGER.log(Level.WARNING, "El telefono es obligatorio para el registro" );
            throw new AtributosVaciosException("El telefono es obligatorio");
        }

        if(email == null || email.isBlank()){
            LOGGER.log(Level.WARNING, "El email es obligatorio para el registro" );
            throw new AtributosVaciosException("El email es obligatorio");
        }

        if(ciudad == null || ciudad.isBlank()){
            LOGGER.log(Level.WARNING, "La ciudad es obligatoria para el registro" );
            throw new AtributosVaciosException("La ciudad es obligatoria");
        }

        if(direccion == null || direccion.isBlank()){
            LOGGER.log(Level.WARNING, "La direccion es obligatoria para el registro" );
            throw new AtributosVaciosException("La direccion es obligatoria");
        }

        if(clienteEncontrado != null){
            LOGGER.log(Level.SEVERE, "La cedula " + cedula + " ya esta registrada" );
            throw new CLienteYaExistenteException("El cliente ya existe");
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
     * @throws ClienteNoRegistradoException
     */

    public void actualizarCliente(String cedula, String nombre, String telefono, String email, String ciudad, String direccion)throws AtributosVaciosException, ClienteNoRegistradoException{

        Cliente clienteEncontrado = obtenerCliente(cedula);

        if(cedula == null || cedula.isBlank()){
            LOGGER.log(Level.WARNING, "La cedula es obligatoria para el registro" );
            throw new AtributosVaciosException("La cedula es obligatoria");
        }

        if(nombre == null || nombre.isBlank()){
            LOGGER.log(Level.WARNING, "El nombre es obligatorio para el registro" );
            throw new AtributosVaciosException("El nombre es obligatorio");
        }

        if(telefono == null || telefono.isBlank()){
            LOGGER.log(Level.WARNING, "El telefono es obligatorio para el registro" );
            throw new AtributosVaciosException("El telefono es obligatorio");
        }

        if(email == null || email.isBlank()){
            LOGGER.log(Level.WARNING, "El email es obligatorio para el registro" );
            throw new AtributosVaciosException("El email es obligatorio");
        }

        if(ciudad == null || ciudad.isBlank()){
            LOGGER.log(Level.WARNING, "La ciudad es obligatoria para el registro" );
            throw new AtributosVaciosException("La ciudad es obligatoria");
        }

        if(direccion == null || direccion.isBlank()){
            LOGGER.log(Level.WARNING, "La direccion es obligatoria para el registro" );
            throw new AtributosVaciosException("La direccion es obligatoria");
        }

        if(clienteEncontrado == null){
            LOGGER.log(Level.SEVERE, "La cedula " + cedula + " no esta registrada" );
            throw new ClienteNoRegistradoException("El cliente no ha sido registrado");
        }
        clienteEncontrado.setNombre(nombre);
        clienteEncontrado.setTelefono(telefono);
        clienteEncontrado.setCiudad(ciudad);
        clienteEncontrado.setDireccion(direccion);
        LOGGER.log(Level.INFO, "Se ha actualizado el cliente con cedula: " + cedula + "");
    }

    /**
     * Elimina un cliente dado su cedula
     * @param cedula
     * @throw ClienteNoRegistradoException
     */

    public void eliminarCliente(String cedula)throws ClienteNoRegistradoException {
        Cliente clientePorEliminar = null;
        for(Cliente cliente : listaClientes){
            if(cliente.getCedula().equals(cedula)){
                clientePorEliminar = cliente;
            }
        }
        if(clientePorEliminar != null){
            LOGGER.log(Level.INFO, "Se ha eliminado el cliente con cedula: " + cedula + "");
            listaClientes.remove(clientePorEliminar);
        }else{
            LOGGER.log(Level.SEVERE, "La cedula " + cedula + " no esta registrada" );
            throw new ClienteNoRegistradoException("El cliente con cedula" + cedula + " no esta registrado");
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
            throw new AtributosVaciosException("La placa es obligatoria");
        }

        if(referencia == "" || referencia.isEmpty()){
            LOGGER.log(Level.WARNING, "La referencia es obligatoria para el registro" );
            throw new AtributosVaciosException("La referencia es obligatoria");
        }

        if(marcaVehiculo == null){
            LOGGER.log(Level.WARNING, "La marca es obligatoria para el registro" );
            throw new AtributosVaciosException("La marca es obligatoria");
        }

        if(modelo == "" || modelo.isEmpty()){
            LOGGER.log(Level.WARNING, "El modelo es obligatorio para el registro" );
            throw new AtributosVaciosException("El modelo es obligatorio");
        }

        if(imagenVehiculo == null || imagenVehiculo == ""){
            LOGGER.log(Level.WARNING, "La imagen es obligatoria para el registro" );
            throw new AtributosVaciosException("La imagen es obligatoria");
        }

        if(kilometraje < 0){
            LOGGER.log(Level.WARNING, "El kilometraje debe ser mayor a 0" );
            throw new AtributoNegativoException("El kilometraje no puede ser negativo");
        }

        if(precioAlquiler < 0){
            LOGGER.log(Level.WARNING, "El precio debe ser mayor a 0" );
            throw new AtributoNegativoException("El precio no puede ser negativo");
        }

        if(tipoCajaVehiculo == null){
            LOGGER.log(Level.WARNING, "La caja del vehiculo es obligatoria para el registro" );
            throw new AtributosVaciosException("Es obligatoria la caja del vehiculo");
        }

        if(numSillas < 0){
            LOGGER.log(Level.WARNING, "El numero debe ser mayor a 0" );
            throw new AtributoNegativoException("El mumero de sillas no puede ser negativo");
        }

        if(vehiculoAsociado != null){
            LOGGER.log(Level.SEVERE, "La placa " + placa + " ya esta registrada" );
            throw new VehiculoYaExistenteException("La placa ya existe");
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
            throw new AtributoNegativoException("El kilometraje no puede ser negativo");
        }

        if(precioAlquiler < 0){
            LOGGER.log(Level.WARNING, "El precio debe ser mayor a 0" );
            throw new AtributoNegativoException("El precio no puede ser negativo");
        }

        if(tipoCajaVehiculo == null){
            LOGGER.log(Level.WARNING, "La caja del vehiculo es obligatoria para podre actualizar" );
            throw new AtributosVaciosException("Es obligatoria la caja del vehiculo");
        }

        if(vehiculoEncontrado == null){
            LOGGER.log(Level.SEVERE, "La placa " + placa + " no esta registrada" );
            throw new VehiculoNoRegistradoException("El vehiculo no esta registrado");
        }
        vehiculoEncontrado.setKilometraje(kilometraje);
        vehiculoEncontrado.setPrecioAlquiler(precioAlquiler);
        vehiculoEncontrado.setTipoCajaVehiculo(tipoCajaVehiculo);
        LOGGER.log(Level.INFO, "Se ha actualizado el vehículo con placa: " + placa + "");
    }


    /**
     * Elimina un vehiculo dada su placa
     * @param placa
     * @throws VehiculoNoRegistradoException
     */

    public void eliminarVehiculo(String placa) throws VehiculoNoRegistradoException{
        Vehiculo vehiculoPorEliminar = obtenerVehiculo(placa);
        for(Vehiculo vehiculo : listaVehiculos){
            if(vehiculo.getPlaca().equals(placa)){
                vehiculoPorEliminar = vehiculo;
            }
        }
        if(vehiculoPorEliminar != null){
            LOGGER.log(Level.INFO, "Se ha eliminado el vehículo con placa: " + placa + "");
            listaVehiculos.remove(vehiculoPorEliminar);
        }else{
            LOGGER.log(Level.SEVERE, "La placa " + placa + " no esta registrada" );
            throw new VehiculoNoRegistradoException("El vehiculo con "+ placa + " no esta registrado");
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
            throw new AtributosVaciosException("La cédula del cliente es un campo obligatorio");
        }
        if (vehiculo == null) {
            LOGGER.log(Level.WARNING, "El vehiculo es un campo obligatorio");
            throw new AtributosVaciosException("Es necesario seleccionar un vehículo");
        }
        if (fechaInicial == null) {
            LOGGER.log(Level.WARNING, "La fecha de inicio es un campo obligatorio");
            throw new AtributosVaciosException("Debe seleccionar un valor en la fecha inicial");
        }
        if (fechaFinal == null) {
            LOGGER.log(Level.WARNING, "La fecha final es un campo obligatorio");
            throw new AtributosVaciosException("Debe seleccionar un valor en la fecha final");
        }
        if (!fechaInicial.isBefore(fechaFinal)) {
            LOGGER.log(Level.WARNING, "La fecha inicial no puede ser mayor a la fecha final");
            throw new FechaInvalidaException("La fecha inicial no puede estar después de la fecha final");
        }
        //Calcula los dias
        long dias = fechaInicial.until(fechaFinal, ChronoUnit.DAYS);
        Cliente cliente = obtenerCliente(cedulaCliente);
        if (cliente == null) {
            LOGGER.log(Level.WARNING, "La fecha inicial no puede ser mayor a la fecha final");
            throw new ClienteNoRegistradoException("No existe un cliente que tenga esta cédula");
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
        LOGGER.log(Level.INFO, "Se ha creado un nuevo registro asignado a la cedula: " + cedulaCliente + "");
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
        LOGGER.log(Level.INFO, "Los vehículos " + vehiculosDisponibles + "se encuentran disponibles");
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
            LOGGER.log(Level.WARNING, "La fecha de inicio no se ha seleccionado");
            throw new AtributosVaciosException("Debe seleccionar un valor en la fecha inicial");
        }
        if (fechaFinal == null) {
            LOGGER.log(Level.WARNING, "La fecha final no se ha seleccionado");
            throw new AtributosVaciosException("Debe seleccionar un valor en la fecha final");
        }
        if (!fechaInicial.isBefore(fechaFinal)) {
            LOGGER.log(Level.WARNING, "La fecha inicial no puede ser mayor a la fecha final");
            throw new FechaInvalidaException("La fecha inicial no puede estar después de la fecha final");
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
        LOGGER.log(Level.INFO, "Los vehículos " + vehiculosAlquilados + "se encuentran alquilados");
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
        LOGGER.log(Level.INFO, "El total ganado es: " + total + "");
        return total;
    }

    

}
