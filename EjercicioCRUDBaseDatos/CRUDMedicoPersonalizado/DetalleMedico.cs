using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using CRUDMedicoPersonalizado.DAO;
using static System.Net.Mime.MediaTypeNames;

namespace CRUDMedicoPersonalizado
{
    public partial class DetalleMedico : Form
    {
        private Medico m;
        public int id;
        private MedicoDAO mDAO;
        public DetalleMedico()
        {
            InitializeComponent();
            mDAO = new MedicoDAO();
        }

        private void btnAceptar_Click(object sender, EventArgs e)
        {
            if (!Validar()) return;
            CargarDatosProducto();

            if (mDAO.modificarMedicos(m) != 0)
            {
                MessageBox.Show("Producto modificado correctamente");
            }
            else
            {
                MessageBox.Show("Error al modificar el medico");
            }
            Dispose();
        }

        private bool Validar()
        {
            // Validar el nombre del medico.
            if (textBox2.Text.Trim().Equals(""))
            {
                MessageBox.Show("Ingrese el nombre del medico");
                return false;
            }

            // Validar la especializacion del medico
            if (comboBox1.Text.Trim().Equals(""))
            {
                MessageBox.Show("Ingrese la especializacion del medico");
                return false;
            }

            // Validar la direccion del medico.
            if (textBox3.Text.Trim().Equals(""))
            {
                MessageBox.Show("Ingrese la direccion");
                return false;
            }

            return true;
        }

        private void CargarDatosProducto()
        {
            m = new Medico();
            m.Id = getIdIfExist();
            m.Nombre = textBox2.Text.Trim();
            m.Especialidad = comboBox1.Text.Trim();
            m.Direccion = textBox3.Text.Trim();
        }
        private int getIdIfExist()
        {
            if (!numericUpDown1.Text.Trim().Equals(""))
            {
                if (int.TryParse(numericUpDown1.Text.Trim(), out int id))
                    return id;
                else
                    return -1;
            }
            else
                return -1;
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            //Select
            Medico med = mDAO.leerUno(id);
            //Cargar datos en los controles
            numericUpDown1.Text = id.ToString();
            textBox2.Text = med.Nombre;
            comboBox1.Text = med.Especialidad.ToString();
            textBox3.Text = med.Direccion.ToString();
        }

        private void btnCancelar_Click(object sender, EventArgs e)
        {
            Dispose();
        }

        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {
            numericUpDown1.Enabled = false;
        }
    }
}
