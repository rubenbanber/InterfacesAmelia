using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CRUDMedicoPersonalizado
{
    internal class Medico
    {
        private int id;
        private String nombre;
        private String especialidad;
        private String direccion;

        public Medico()
        {
        }

        public Medico(int id, string nombre, string especialidad, string direccion)
        {
            this.id = id;
            this.nombre = nombre;
            this.especialidad = especialidad;
            this.direccion = direccion;
        }

        public int Id { get => id; set => id = value; }
        public string Nombre { get => nombre; set => nombre = value; }
        public string Especialidad { get => especialidad; set => especialidad = value; }
        public string Direccion { get => direccion; set => direccion = value; }
    }
}
