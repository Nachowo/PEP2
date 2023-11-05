import React, { Component } from 'react';
import Button from '@mui/material/Button';
import cuotaService from '../services/cuotaService';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';

class VerCuotas extends Component {
  constructor(props) {
    super(props);

    this.state = {
      cuotas: [],
      error: null,
      alumno: null,
    };

    this.pagar = this.pagar.bind(this);
  }

  pagar(id) {
    console.log(id);
    cuotaService
      .pagarCuota(id)
      .then((res) => {
        window.location.reload();
      })
      .catch((e) => {
        console.log(e);
      });
  }

  getCuotas(id) {
    this.state.cuotas = [];
    cuotaService
      .getCuotasByAlumno(id)
      .then((res) => {
        console.log(res);
        this.setState({ cuotas: res.data, error: null });
      })
      .catch((e) => {
        console.log(this.state.cuotas);
        console.log('Error al obtener los alumnos');
        console.log(e);
        this.setState({
          error: 'Error al obtener las cuotas.',
          cuotas: [],
        });
      });
    if (!this.state.error === null) {
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

  render() {
    return (
      <div>
        <h2>Listar Cuotas</h2>
        <br></br>
        <TableContainer component={Paper}>
        <style>{`
       
       h2 {
         margin-bottom: 20px;
         text-align: center;
       }
       
     `}</style>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Numero de cuota</TableCell>
                <TableCell>Status</TableCell>
                <TableCell>Descuento fijo</TableCell>
                <TableCell>Fecha de vencimiento</TableCell>
                <TableCell>Fecha de Pago</TableCell>
                <TableCell>Monto a pagar</TableCell>
                <TableCell>Acciones</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.cuotas.map((cuota, index) => (

                 <TableRow key={cuota.id} style={{ backgroundColor: index % 2 === 0 ? '#2196f3' : '#1769aa' }}>
                   <TableCell>{cuota.numeroCuota}</TableCell>
                   <TableCell>{cuota.status}</TableCell>
                   <TableCell>{cuota.descuento}</TableCell>
                   <TableCell>{cuota.fechaVencimiento}</TableCell>
                   <TableCell>{cuota.fechaPago}</TableCell>
                   <TableCell>{cuota.monto}</TableCell>
                   <TableCell>
                     {cuota.status === 'pendiente' && (
                       <Button variant="contained" color="primary" onClick={() => this.pagar(cuota.id)} className="btn btn-info">
                         Pagar
                       </Button>
                     )}
                   </TableCell>
                 </TableRow>
               ))}
             </TableBody>
          </Table>
        </TableContainer>
      </div>
    );
  }
}

export default VerCuotas;
