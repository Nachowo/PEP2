import React from 'react';
import notaService from '../services/notaService';

const Principal = () => {
  const archivoHandler = async (e) => {
    const archivo = e.target.files[0];

    if (archivo) {
      const formData = new FormData();
      formData.append('file', archivo);

      try {
        // Sube el archivo utilizando notaService
        const response = await notaService.subirNotas(formData);

        if (response.status === 200) {
          alert('Archivo subido exitosamente');
          console.log('Archivo subido exitosamente');
        } else {
          alert('Error al subir el archivo');
          console.error('Error al subir el archivo');
        }
      }catch (e) {
        console.error(e);
      } 
    }
  };

  return (
    <div>
      <h2>Esta es la página principal</h2>
      <form encType="multipart/form-data">
        <input type="file" onChange={archivoHandler} />
      </form>
    </div>
  );
}

export default Principal;
