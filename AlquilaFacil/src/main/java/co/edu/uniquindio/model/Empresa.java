package co.edu.uniquindio.model;

import co.edu.uniquindio.exceptions.*;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public Cliente crearCliente(String cedula, String nombre, String telefono, String email, String ciudad, String direccion) throws AtributosVaciosException, CLienteYaExistenteException{

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
     * @throw ClienteNoRegistradoException
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
        clienteEncontrado.setCedula(cedula);
        clienteEncontrado.setNombre(nombre);
        clienteEncontrado.setTelefono(telefono);
        clienteEncontrado.setCiudad(ciudad);
        clienteEncontrado.setDireccion(direccion);
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

    //VALIDAR LOS DATOS COMO KILOMETRAJE.
    public Vehiculo crearVehiculo(String placa,String referencia, MarcaVehiculo marcaVehiculo,String modelo,ArrayList<String> rutaImagenes, double kilometraje, double precioAlquiler, TipoCajaVehiculo tipoCajaVehiculo, int numSillas) throws AtributosVaciosException, AtributoNegativoException, VehiculoYaExistenteException {

        Vehiculo vehiculoAsociado = obtenerVehiculo(placa);

        if(placa == null || placa.isBlank()){
            LOGGER.log(Level.WARNING, "La placa es obligatoria para el registro" );
            throw new AtributosVaciosException("La placa es obligatoria");
        }

        if(referencia == null || referencia.isBlank()){
            LOGGER.log(Level.WARNING, "La referencia es obligatoria para el registro" );
            throw new AtributosVaciosException("La referencia es obligatoria");
        }

        if(marcaVehiculo == null){
            LOGGER.log(Level.WARNING, "El telefono es obligatorio para el registro" );
            throw new AtributosVaciosException("El telefono es obligatorio");
        }

        if(modelo == null || modelo .isBlank()){
            LOGGER.log(Level.WARNING, "El modelo es obligatorio para el registro" );
            throw new AtributosVaciosException("El modelo es obligatorio");
        }

        if(rutaImagenes == null){
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
                .rutasImagenes(rutaImagenes)
                .kilometraje(kilometraje)
                .precioAlquiler(precioAlquiler)
                .tipoCajaVehiculo(tipoCajaVehiculo)
                .numSillas(numSillas)
                .build();

        listaVehiculos.add(vehiculoNuevo);

        LOGGER.log(Level.INFO, "Se ha registrado un nuevo cliente con cedula: " + placa + "");
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
    }


    /**
     * Elimina un vehiculo dada su placa
     * @param placa
     * @throws VehiculoNoRegistradoException
     */

    public void eliminarVehiculo(String placa)throws VehiculoNoRegistradoException{
        Vehiculo vehiculoPorEliminar = obtenerVehiculo(placa);
        for(Vehiculo vehiculo : listaVehiculos){
            if(vehiculo.getPlaca().equals(placa)){
                vehiculoPorEliminar = vehiculo;
            }
        }
        if(vehiculoPorEliminar != null){
            listaVehiculos.remove(vehiculoPorEliminar);
        }else{
            LOGGER.log(Level.SEVERE, "La placa " + placa + " no esta registrada" );
            throw new VehiculoNoRegistradoException("El vehiculo con "+ placa + " no esta registrada");
        }
    }

    //CRUD REGISTRO================================================================================================

    /**
     * Obtiene el registro dado el cliente
     * @param Cliente
     * @return
     */

    public Registro obtenerRegistro(Cliente cliente){
        Registro registroEncontrado = null;
        for(Registro registro : listaRegistros){
            if(registro.getCliente().equals(cliente)){
                registroEncontrado = registro;
            }
        }
        return registroEncontrado;
    }

    /**
     * Retorna el valor booleano si se pudo crear el registro
     * @param cliente
     * @param vehiculo
     * @param precioFactura
     * @param fechaAlquiler
     * @param fechaRegreso
     * @return
     * @throw RegistroYaCreadoException
     */

    public Registro crearRegistro(Cliente cliente, String cedulaCliente, Vehiculo vehiculo, String placaVehiculo, LocalDateTime fechaRegistro, LocalDate fechaInicio, LocalDate fechaRegreso, double precioFactura)throws Exception, AtributosVaciosException, AtributoNegativoException, RegistroYaExistenteException {

        Registro registroAsociado = obtenerRegistro(cliente);
        Cliente clientePorRegistrar = obtenerCliente(cedulaCliente);
        Vehiculo vehiculoPorRegistrar = obtenerVehiculo(placaVehiculo);

        if (clientePorRegistrar == null || clientePorRegistrar.equals("")) {
            LOGGER.log(Level.WARNING, "El cliente es obligatorio para el registro");
            throw new AtributosVaciosException("El cliente es obligatorio");
        }

        if (vehiculoPorRegistrar == null || vehiculoPorRegistrar.equals("")) {
            LOGGER.log(Level.WARNING, "La placa es obligatoria para el registro");
            throw new AtributosVaciosException("La placa es obligatoria");
        }

        if (fechaRegistro == null) {
            LOGGER.log(Level.WARNING, "La fecha de registro es obligatoria para el registro");
            throw new AtributosVaciosException("La fecha de registro es obligatoria");
        }

        if (fechaInicio == null) {
            LOGGER.log(Level.WARNING, "La fecha de inicio es obligatoria para el registro");
            throw new AtributosVaciosException("La fecha de inicio es obligatoria");
        }

        if (fechaInicio.isAfter(fechaRegreso)) {
            LOGGER.log(Level.WARNING, "La fecha de inicio no puede estar despues de la ficha final");
            throw new Exception("La fecha de inicio no puede estar después de la fecha final");
        }

        //Cuenta la diferencia de dias
        long dias = fechaInicio.until(fechaRegreso, ChronoUnit.DAYS);

        if (fechaRegreso == null) {
            LOGGER.log(Level.WARNING, "La fecha de regreso es obligatoria para el registro");
            throw new AtributosVaciosException("La fecha de regreso es obligatoria");
        }

        if (fechaRegreso.isBefore(fechaInicio)) {
            LOGGER.log(Level.WARNING, "La fecha de regreso no puede estar antes de la fecha de incio");
            throw new Exception("La fecha de regreso no puede estar antes de la fecha de incio");
        }

        if (precioFactura < 0) {
            LOGGER.log(Level.WARNING, "El precio debe ser mayor a 0");
            throw new AtributoNegativoException("El precio no puede ser negativo");
        }

        if (registroAsociado != null) {
            LOGGER.log(Level.SEVERE, "El cliente " + cliente + " ya se le asigno un registro");
            throw new RegistroYaExistenteException("Ya se le asigno un registro");
        } else {

            Registro registroNuevo = Registro.builder()
                    .cliente(clientePorRegistrar)
                    .vehiculo(vehiculoPorRegistrar)
                    .fechaRegistro(fechaRegistro)
                    .fechaInicio(fechaInicio)
                    .fechaRegreso(fechaRegreso)
                    .precioFactura(precioFactura)
                    .build();
            listaRegistros.add(registroNuevo);
            return registroNuevo;
        }
    }

    /**
     * Elmina el registro segun el cliente asociado
     * @param cliente
     * @throw RegistroNoRegistradoException
     */

    public void eliminarRegistro(Cliente cliente)throws RegistroNoRegistradoException {
        Registro registroPorEliminar = null;
        for (Registro registro : listaRegistros) {
            if (registro.getCliente().equals(cliente)) {
                registroPorEliminar = registro;
            }
        }
        if (registroPorEliminar != null) {
            listaRegistros.remove(registroPorEliminar);
        } else {
            LOGGER.log(Level.SEVERE, "El registro asignado al cliente  " + cliente + " no esta creado" );
            throw new RegistroNoRegistradoException("El registro para el cliente " + cliente + " no se ha creado");
        }
    }

}
