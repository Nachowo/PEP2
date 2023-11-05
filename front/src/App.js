import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HeaderComponent from './components/HeaderComponent';
import CrearAlumno from './components/CrearAlumno';
import ListarAlumnos from './components/ListarAlumnos';
import Princial from './components/Principal';
import VerCuotas from './components/VerCuotas';
import VerReporte from './components/VerReporte';

function App() {
  return (
    <div>
        <Router>
              <HeaderComponent />
                <div className="container">
                    <Routes> 
                          <Route path="/" element={<Princial />} />
                          <Route path="/crear-alumno" element={<CrearAlumno />} />
                          <Route path="/listar-alumnos" element={<ListarAlumnos />} />
                          <Route path="/ver-cuotas/:id" element={<VerCuotas />} />
                          <Route path="/ver-rerporte" element={<VerReporte />} />
                    </Routes>
                </div>
        </Router>
    </div>
    
  );
}

export default App;
