using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using CRUDMedicoPersonalizado.DAO;
using static System.Net.Mime.MediaTypeNames;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace CRUDMedicoPersonalizado
{
    public partial class Form1 : Form
    {
        List<Medico> listaMedicos = new List<Medico>();
        MedicoDAO medicoDAO = new MedicoDAO();
        private Medico m;
        public Form1()
        {
            InitializeComponent();
            CargarListaMedicos();
        }

        public void limpiarDatosMedicos()
        {
            numericUpDown1.Value = 0;
            textBox2.Clear();
            comboBox1.SelectedIndex = -1;
            textBox3.Clear();
        }

        private void CargarListaMedicos(string filtro = "")
        {
            dgvMedicos.AllowUserToAddRows = false;
            //VACIO DataGridView
            dgvMedicos.Rows.Clear();
            dgvMedicos.Refresh();
            //VACIO lista
            listaMedicos.Clear();
            //Cargo la lista con la select
            listaMedicos = medicoDAO.consultarMedico(filtro);

            for (int i = 0; i < listaMedicos.Count(); i++)
            {

                dgvMedicos.Rows.Add(
                    listaMedicos[i].Id,
                    listaMedicos[i].Nombre,
                    listaMedicos[i].Especialidad,
                    listaMedicos[i].Direccion);
            }
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

        private void btnSalir_Click(object sender, EventArgs e)
        {
            Dispose();
        }

        private void btnAlta_Click(object sender, EventArgs e)
        {
            if (!Validar()) return;
            //PASAIS DE LOS CONTROLES AL OBJETO PRODUCTO
            CargarDatosMedicos();
            //INSERT
            if (medicoDAO.agregarMedico(m) != 0)
            {
                MessageBox.Show("Producto agregado correctamente");
                CargarListaMedicos();
                limpiarDatosMedicos();
            }
            else
            {
                MessageBox.Show("Error al agregar el producto");
            }
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

        private void CargarDatosMedicos()
        {
            m = new Medico();
            m.Id = getIdIfExist();
            m.Nombre = textBox2.Text.Trim();
            m.Especialidad = comboBox1.Text.Trim();
            m.Direccion = textBox3.Text.Trim();
        }

        private void dgvMedicos_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (dgvMedicos.Columns[e.ColumnIndex].Name == "Modificar")
            {
                DetalleMedico Det = new DetalleMedico();
                Det.id = Convert.ToInt32(numericUpDown1.Text);
                Det.ShowDialog();
                limpiarDatosMedicos();
                CargarListaMedicos();
            }

            if (dgvMedicos.Columns[e.ColumnIndex].Name == "Eliminar")
            {

                int id;
                id = getIdIfExist();
                if (id == -1) return;
                if (MessageBox.Show("¿Desea eliminar el medico?", "Eliminar medico", MessageBoxButtons.YesNo) == DialogResult.Yes)
                {
                    if (medicoDAO.eliminarMedico(id) != 0)
                    {
                        MessageBox.Show("Medico eliminado con éxito.");
                        CargarListaMedicos();
                        limpiarDatosMedicos();
                    }
                    else
                    {
                        MessageBox.Show("Error al eliminar el medico.");
                    }
                }
            }

        }

        private void dgvMedicos_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            // Verificar si el índice de fila es válido
            if (e.RowIndex >= 0 && e.RowIndex < dgvMedicos.Rows.Count)
            {
                // Obtener la fila seleccionada
                DataGridViewRow fila = dgvMedicos.Rows[e.RowIndex];

                // Cargar los valores en los controles
                numericUpDown1.Text = Convert.ToString(fila.Cells["Id"].Value);
                textBox2.Text = Convert.ToString(fila.Cells["Nombre"].Value);
                comboBox1.Text = Convert.ToString(fila.Cells["Especialidad"].Value);
                textBox3.Text = Convert.ToString(fila.Cells["Direccion"].Value);
            }

        }
    }
}

