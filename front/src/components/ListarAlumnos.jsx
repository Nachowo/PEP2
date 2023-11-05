import React, { useState, useEffect } from 'react';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import alumnoService from '../services/alumnoService';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function ListarAlumnos() {
  const [alumnos, setAlumnos] = useState([]);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const verAlumno = (id) => {
    navigate(`/ver-cuotas/${id}`);
  };

  const getAlumnos = () => {
    console.log("getAlumnos");
    alumnoService
      .getAlumnos()
      .then((res) => {
        setAlumnos(res);
        setError(null);
      })
      .catch((e) => {
        console.log("Error al obtener los alumnos");
        console.log(e);
        setError('Error al obtener los alumnos.');
        setAlumnos([]);
        alert(error);
      });
  };

  useEffect(() => {
    getAlumnos();
  }, []);

  return (
    <div>
      <h2>Listar Alumnos</h2>
      <br />
      <TableContainer component={Paper}>
      <style>{`
       
       h2 {
         margin-bottom: 20px;
         text-align: center;
       }
       
     `}</style>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Rut</TableCell>
              <TableCell>Nombre</TableCell>
              <TableCell>Apellido</TableCell>
              <TableCell>Nacimiento</TableCell>
              <TableCell>Tipo Colegio</TableCell>
              <TableCell>Nombre Colegio</TableCell>
              <TableCell>Año Egreso</TableCell>
              <TableCell>Tipo Pago</TableCell>
              <TableCell>Cantidad Cuotas</TableCell>
              <TableCell>Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {alumnos.map((alumno,index) => (
              <TableRow key={alumno.id} style={{ backgroundColor: index % 2 === 0 ? '#2196f3' : '#1769aa' }}>                <TableCell>{alumno.rut}</TableCell>
                <TableCell>{alumno.nombre1}</TableCell>
                <TableCell>{alumno.apellido}</TableCell>
                <TableCell>{alumno.nacimiento}</TableCell>
                <TableCell>{alumno.tipoColegio}</TableCell>
                <TableCell>{alumno.nombreColegio}</TableCell>
                <TableCell>{alumno.anoEgreso}</TableCell>
                <TableCell>{alumno.tipoPago}</TableCell>
                <TableCell>{alumno.cantidadCuotas}</TableCell>
                <TableCell>
                  <Button
                    variant='contained'
                    color='primary'
                    onClick={() => verAlumno(alumno.id)}
                  >
                    Ver Cuotas
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default ListarAlumnos;
