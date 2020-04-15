package com.visorus.test

class Articulo {

	String clave
	String nombre
	String descripcion
	double precio = 0d
	Date fechaAlta = new Date()
	boolean activo = true

	// Usuario usuario

	static constraints = {
		clave(minSize: 3, maxSize: 15, unique: true)
		nombre(minSize: 3, maxSize: 50, nullable: false, blank: false)
		descripcion(minSize: 3, maxSize: 200, nullable: true, blank: true)
		precio(min: 0d)
	}

	Map obtieneDatos() {
		[
		        id: id,
				clave: clave,
				nombre: nombre,
				precio: precio
		]
	}
}
