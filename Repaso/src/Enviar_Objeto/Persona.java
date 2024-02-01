package Enviar_Objeto;

import java.io.Serializable;

public class Persona implements Serializable{

		private String nombre;
		private String apellido;
		
		public Persona(String n , String a) {
			this.nombre = n;
			this.apellido = a;
		
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		
		
		
}
