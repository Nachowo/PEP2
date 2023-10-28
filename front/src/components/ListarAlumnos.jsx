import React,{Component} from 'react';
import alumnoService from '../services/alumnoService';
import Button from '@mui/material/Button';

class ListarAlumnos extends Component{
    constructor(props){
    super(props)

    this.state = {

        alumnos: [],
        error: null
    }
    
    this.verAlumno = this.verAlumno.bind(this);
    }

    verAlumno(id){
        this.props.history.push(`/ver-alumno/${id}`);
    }
    getAlumnos() {
        alumnoService
          .getAlumnos()
          .then((res) => {
            if (res.data.status === true) {
              this.setState({ alumnos: res.data.alumnos, error: null });
            } else {
              this.setState({ error: "Error al recibir los alumnos", alumnos: [] });
            }
          })
          .catch(() => {
            this.setState({ error: 'Error al obtener los alumnos.', alumnos: [] });
          });
      }
    componentDidMount(){
        this.getAlumnos();
    }

    render(){
        return(
        <div>
      <h2>Listar Alumnos</h2>
        <br></br>
        <div className="row">
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Rut</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Nacimiento</th>
                        <th>Tipo Colegio</th>
                        <th>Nombre Colegio</th>
                        <th>Año Egreso</th>
                        <th>Tipo Pago</th>
                        <th>Cantidad Cuotas</th>
                        <th>Acciones</th>
                    </tr>
                </thead>    
                <tbody>
                    {
                        this.state.alumnos.map(
                            alumno =>
                            <tr key={alumno.id}>
                                <td>{alumno.rut}</td>
                                <td>{alumno.nombre1}</td>
                                <td>{alumno.apellido}</td>
                                <td>{alumno.nacimiento}</td>
                                <td>{alumno.tipoColegio}</td>
                                <td>{alumno.nombreColegio}</td>
                                <td>{alumno.anoEgreso}</td>
                                <td>{alumno.tipoPago}</td>
                                <td>{alumno.cantidadCuotas}</td>
                                <td>
                                    <Button variant='contained' color='primary' onClick={ () => this.verAlumno(alumno.id)} className="btn btn-info">Ver</Button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    </div>
    )}

}


export default ListarAlumnos;
