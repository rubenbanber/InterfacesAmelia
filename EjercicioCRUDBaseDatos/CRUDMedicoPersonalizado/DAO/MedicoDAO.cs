using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;
using System.Windows.Forms;
using CRUDMedicoPersonalizado.Conexion;

namespace CRUDMedicoPersonalizado.DAO
{
    internal class MedicoDAO
    {
        //ATRIBUTOS
        private ConexionBase conexion = new ConexionBase();
        public MedicoDAO()
        {
        }

        /// <summary>
        /// Inserta un nuevo producto en la BBDD
        /// </summary>
        /// <param name="med">Instancia de un medico</param>
        /// <returns>Un entero para saber si la insert se ha ejecutado</returns>
        public int agregarMedico(Medico med)
        {
            int resul = 0;
            string strINSERT = "INSERT INTO medicos (nombre, especialidad, direccion) values (@nombre, @especialidad, @direccion);";
            try
            {
                MySqlCommand mCommand = new MySqlCommand(strINSERT, conexion.abrirConexion());
                mCommand.Parameters.Add(new MySqlParameter("@nombre", med.Nombre));
                mCommand.Parameters.Add(new MySqlParameter("@especialidad", med.Especialidad));
                mCommand.Parameters.Add(new MySqlParameter("@direccion", med.Direccion));

                resul = mCommand.ExecuteNonQuery();

            }
            catch (MySqlException ex)
            {
                /*
                ExcepcionAlmacen e = new ExcepcionAlmacen();
                e.CodigoError = ex.ErrorCode;
                e.MensajeAdministrador = ex.Message;
                e.SentenciaSQL = strINSERT;
                switch (ex.Number)
                {
                    case 1062:
                        //CLAVE DUPLICADA
                        e.MensajeUsuario = "Clave duplicada";
                        break;
                }
                */
            }
            finally
            {
                conexion.cerrarConexion();
            }
            return resul;
        }


        public int modificarMedicos(Medico med)
        {
            int resul = 0;
            string strUPDATE = " UPDATE medicos " +
                   "SET nombre = @nombre, " +
                   "especialidad = @especialidad, " +
                   "direccion = @direccion " +
                   "WHERE idMedico = @id";
            try
            {
                MySqlCommand mCommand = new MySqlCommand(strUPDATE, conexion.abrirConexion());
                mCommand.Parameters.Add(new MySqlParameter("@nombre", med.Nombre));
                mCommand.Parameters.Add(new MySqlParameter("@especialidad", med.Especialidad));
                mCommand.Parameters.Add(new MySqlParameter("@direccion", med.Direccion));
                mCommand.Parameters.Add(new MySqlParameter("@id", med.Id));
                resul = mCommand.ExecuteNonQuery();
            }
            catch (MySqlException ex)
            {
                MessageBox.Show(ex.Message); //Si existe un error aquí muestra el mensaje
            }
            finally
            {
                conexion.cerrarConexion();
            }
            return resul;
        }

        public int eliminarMedico(int id)
        {
            int resul = 0;
            string strDELETE = " DELETE FROM medicos WHERE idMedico=@id";
            try
            {
                MySqlCommand mCommand = new MySqlCommand(strDELETE, conexion.abrirConexion());
                mCommand.Parameters.Add(new MySqlParameter("@id", id));
                resul = mCommand.ExecuteNonQuery();
            }
            catch (MySqlException ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                conexion.cerrarConexion();
            }
            return resul;
        }
        public Medico leerUno(int id)
        {
            MySqlDataReader mReader = null;
            Medico m = new Medico();
            string strCONSULTA = "SELECT * FROM medicos WHERE idMedico=@id";

            try
            {
                MySqlCommand mCommand = new MySqlCommand(strCONSULTA);
                mCommand.Parameters.Add(new MySqlParameter("@id", id));
                mCommand.Connection = conexion.abrirConexion();
                mReader = mCommand.ExecuteReader();

                while (mReader.Read())
                {
                    m.Id = mReader.GetInt16("idMedico");
                    m.Nombre = mReader.GetString("nombre");
                    m.Especialidad = mReader.GetString("especialidad");
                    m.Direccion = mReader.GetString("direccion");
                }
                mReader.Close();
            }
            catch (MySqlException ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                conexion.cerrarConexion();
            }
            return m;
        }
        public List<Medico> consultarMedico(string filtro)
        {
            List<Medico> lisMedico = new List<Medico>();
            MySqlDataReader mReader = null;
            Medico m;
            string strCONSULTA = "SELECT * FROM medicos";
            if (filtro != "")
            {
                strCONSULTA += " WHERE " +
                    "nombre LIKE '%" + filtro + "%' OR " +
                    "especialidad LIKE '%" + filtro + "%' OR " +
                    "direccion LIKE '%" + filtro + "%';";
            }
            try
            {
                MySqlCommand mCommand = new MySqlCommand(strCONSULTA);
                mCommand.Connection = conexion.abrirConexion();
                mReader = mCommand.ExecuteReader();

                while (mReader.Read())
                {
                    m = new Medico();
                    m.Id = mReader.GetInt16("idMedico");
                    m.Nombre = mReader.GetString("nombre");
                    m.Especialidad = mReader.GetString("especialidad");
                    m.Direccion = mReader.GetString("direccion");

                    lisMedico.Add(m);
                }
                mReader.Close();
            }
            catch (MySqlException ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                conexion.cerrarConexion();
            }
            return lisMedico;
        }
    }
}
