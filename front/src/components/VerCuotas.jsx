import React,{Component} from 'react';
import Button from '@mui/material/Button';
import cuotaService from '../services/cuotaService';

class VerCuotas extends Component{
    constructor(props){
    super(props)

    this.state = {
        cuotas: [],
        error: null,
        alumno: null
    }
    
    this.pagar = this.pagar.bind(this);
    }

    pagar(id){
        console.log(id);
        cuotaService.pagarCuota(id).then((res) => {
            window.location.reload();
        }).catch((e) => {
            console.log(e);
            
        });
    }
    getCuotas(id) {
        this.state.cuotas = [];
        cuotaService
          .getCuotasByAlumno(id)
          .then((res) => {
            console.log(res);
            this.setState({ cuotas: res.cuotas, error: null });})
          .catch((e) => {
            console.log(this.state.cuotas)
            console.log("Error al obtener los alumnos");
            console.log(e);
            this.setState({ error: 'Error al obtener las cuotas.', cuotas: [] });
          });
          if(!this.state.error===null){
            alert(this.state.error);
      }
    }


      componentDidMount() {
        // Obtener el id de la URL utilizando React Router
        const id = Number(window.location.pathname.split('/').pop());
        console.log(id);
        this.setState({ alumno: id });
        console.log(this.state.alumno);
        // Llamar a la función para obtener las cuotas
        this.getCuotas(id);
      }
    

    render(){
        return(
        <div>
      <h2>Listar Cuotas</h2>
        <br></br>
        <div className="row">
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Numero de cuota</th>
                        <th>Status</th>
                        <th>descuento fijo</th>
                        <th>Fecha de vencimiento</th>
                        <th>Fecha de Pago</th>
                        <th>Monto a pagar</th>
                    </tr>
                </thead>    
                <tbody>
                    {   
                        this.state.cuotas.map(
                            cuota =>
                            <tr key={cuota.id}>
                                <td>{cuota.numeroCuota}</td>
                                <td>{cuota.status}</td>
                                <td>{cuota.descuento}</td>
                                <td>{cuota.fechaVencimiento}</td>
                                <td>{cuota.fechaPago}</td>
                                <td>{cuota.monto}</td>
                                <td>
                                    <Button variant='contained' color='primary' onClick={() => this.pagar(cuota.id)  }  className="btn btn-info">Ver</Button>
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


export default VerCuotas;
