import React, { useState, useEffect } from 'react';
import alumnoService from '../services/alumnoService';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';

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
      });
    if (error !== null) {
      alert(error);
    }
  };

  useEffect(() => {
    getAlumnos();
  }, []);
//useEffect(() => {
//  getAlumnos();
//}, []);
  return (
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
            {alumnos.map((alumno) => (
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
                  <Button
                    variant='contained'
                    color='primary'
                    onClick={() => verAlumno(alumno.id)}
                    className="btn btn-info"
                  >
                    Ver
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ListarAlumnos;
