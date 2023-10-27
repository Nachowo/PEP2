import React, { Component } from 'react'
import alumnoService from '../services/alumnoService';
//import BookService from '../services/BookService';

class crearAlumno extends Component {
    constructor(props) {
        super(props)

        this.state = {
            rut: '',
            nombre: '',
            apellido: '',
            nacimiento: '',
            tipoColegio: '',
            nombreColegio: '',
            anoEgreso: '',
            tipoPago: '',
            cantidadCuotas: ''
        }
        this.rutHandler = this.rutHandler.bind(this);
    this.nombreHandler = this.nombreHandler.bind(this);
    this.apellidoHandler = this.apellidoHandler.bind(this);
    this.nacimientoHandler = this.nacimientoHandler.bind(this);
    this.tColegioHandler = this.tColegioHandler.bind(this);
    this.nColegioHandler = this.nColegioHandler.bind(this);
    this.egresoHandler = this.egresoHandler.bind(this);
    this.tPagoHandler = this.tPagoHandler.bind(this);
    this.cCuotasHandler = this.cCuotasHandler.bind(this);
        this.saveAlumno = this.saveAlumno.bind(this);

    }


    saveAlumno = (e) => {
        e.preventDefault();
        let alumno = {
            rut: this.state.rut,
            nombre1: this.state.nombre,
            apellido: this.state.apellido,
            nacimiento: this.state.nacimiento,
            tipoColegio: this.state.tipoColegio,
            nombreColegio: this.state.nombreColegio,
            anoEgreso: this.state.anoEgreso,
            tipoPago: this.state.tipoPago,
            cantidadCuotas: this.state.cantidadCuotas
          };
          
          alumnoService.crearAlumno(alumno)
          .then(res => { console.log(res.data);  })
        .catch(err => console.log(err));
    }
    
    rutHandler= (event) => {
        this.setState({rut: event.target.value});
    }
    nombreHandler= (event) => {
        this.setState({nombre: event.target.value});
    }
    apellidoHandler= (event) => {
        this.setState({apellido: event.target.value});
    }
    nacimientoHandler= (event) => {
        this.setState({nacimiento: event.target.value});
    }
    tColegioHandler= (event) => {
        this.setState({tipoColegio: event.target.value});
    }
    nColegioHandler= (event) => {
        this.setState({nombreColegio: event.target.value});
    }
    egresoHandler= (event) => {
        this.setState({anoEgreso: event.target.value});
    }
    tPagoHandler= (event) => {
        this.setState({tipoPago: event.target.value});
    }
    cCuotasHandler= (event) => {
        this.setState({cantidadCuotas: event.target.value});
    }

    cancel(){
        this.props.history.push('/books');
    }

    render() {
        return (
            <div>
  <br></br>
  <div className="container">
    <div className="row">
      <div className="card col-md-6 offset-md-3 offset-md-3">
        <h3 className="text-center">Add Student</h3>
        <div className="card-body">
          <form>
            {/*Campo para ingresar el rut */}
            <div className="form-group">
              <label> Rut: </label>
              <input placeholder="Rut" name="rut" className="form-control" 
                value={this.state.rut} onChange={this.rutHandler}/>
            </div>

            {/*Campo para ingresar el nombre */}
            <div className="form-group">
              <label> Nombre: </label>
              <input placeholder="Ingresar el nombre" name="nombre" className="form-control" 
                value={this.state.nombre} onChange={this.nombreHandler}/>
            </div>

            {/*Campo para ingresar el apellido */}
            <div className="form-group">
              <label> Apellido: </label>
              <input placeholder="Ingresar el apellido" name="apellido" className="form-control" 
                value={this.state.apellido} onChange={this.apellidoHandler}/>
            </div>

            {/*Campo para ingresar la fecha de nacimiento */}
            <div className="form-group">
              <label> Fecha de nacimiento: </label>
              <input placeholder="Ingresar fecha de nacimiento" name="nacimiento" className="form-control" type="date"
                value={this.state.nacimiento} onChange={this.nacimientoHandler}/>
            </div>

            {/*Campo para ingresar el tipo de colegio */}
            {/* Ver que valores uso en el backend */}
            <div className="form-group">
              <label> Tipo de colegio: </label>
              <select placeholder="Seleccione el tipo de colegio" name="tipoColegio" className="form-control" 
              value={this.state.tipoColegio} onChange={this.tColegioHandler}>
                <option value="3">Municipal</option>
                <option value="2">Privado</option>
                <option value="1">Subvencionado</option>
              </select>
            </div>

            {/*Campo para ingresar el nombre del colegio */}
            <div className="form-group">
              <label> Nombre del colegio: </label>
              <input placeholder="Ingresar nombre del colegio" name="nombreColegio" className="form-control" 
                value={this.state.nombreColegio} onChange={this.nColegioHandler}/>
            </div>

            {/*Campo para ingresar el año de egreso */}
            <div className="form-group">
              <label> Año de egreso: </label>
              <input placeholder="Ingresar año de egreso" name="anoEgreso" className="form-control" 
                value={this.state.anoEgreso} onChange={this.egresoHandler}/>
            </div>

            {/*Campo para ingresar el tipo de pago */}
            <div className="form-group">
              <label> Tipo de pago: </label>
              <select name="tipoPago" className="form-control" 
              value={this.state.tipoPago} onChange={this.tPagoHandler}>
                <option value="1">Al contado</option>
                <option value="2">Cuotas</option>
              </select>
            </div>

            {/*Campo para ingresar la cantidad de cuotas */}
            <div className="form-group">
              <label> Cantidad de cuotas: </label>
              <input placeholder="Ingresar cantidad de cuotas" name="cantidadCuotas" className="form-control" 
                value={this.state.cantidadCuotas} onChange={this.cCuotasHandler}/>
            </div>

            <button className="btn btn-success" onClick={this.saveAlumno}>Save</button>
            <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
        )
    }
}

export default crearAlumno
