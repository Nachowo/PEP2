import React, { useState, useEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import alumnoService from '../services/alumnoService';

function VerReporte() {
  const [reportes, setReporte] = useState([]);
  const navigate = useNavigate();

  const obtenerReporte = () => {
    console.log("Obteniendo reporte");
    alumnoService
      .getReporte()
      .then((res) => {
        console.log(res);
        setReporte(res);
      })
      .catch((e) => {
        console.log(e);
        alert("Error al obtener el reporte");
      });
  };

  useEffect(() => {
    obtenerReporte();
  }, []);

  return (
    <div>
      <h2>Reporte</h2>
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
              <TableCell>N° Examenes rendidos</TableCell>
              <TableCell>Promedio examenes</TableCell>
              <TableCell>Total a pagar</TableCell>
              <TableCell>Tipo de pago</TableCell>
              <TableCell>N° de cuotas pactadas</TableCell>
              <TableCell>N° de cuotas pagadas</TableCell>
              <TableCell>Total pagado</TableCell>
              <TableCell>Fecha de ultimo pago</TableCell>
              <TableCell>Saldo por pagar</TableCell>
              <TableCell>N° de cuotas vencidas</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {reportes.map((reporte,index) => (
            <TableRow key={reporte.id} style={{ backgroundColor: index % 2 === 0 ? '#2196f3' : '#1769aa' }}>
                <TableCell>{reporte.rut}</TableCell>
                <TableCell>{reporte.nombre}</TableCell>
                <TableCell>{reporte.cantidad_examenes}</TableCell>
                <TableCell>{reporte.promedio_puntajes}</TableCell>
                <TableCell>{reporte.monto_total_arancel}</TableCell>
                <TableCell>{reporte.tipo_de_pago}</TableCell>
                <TableCell>{reporte.cantidad_cuotas}</TableCell>
                <TableCell>{reporte.cantidad_cuotas_pagadas}</TableCell>
                <TableCell>{reporte.total_pagado}</TableCell>
                <TableCell>{reporte.ultimo_pago}</TableCell>
                <TableCell>{reporte.saldo_por_pagar}</TableCell>
                <TableCell>{reporte.cantidad_cuotas_retraso}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default VerReporte;
