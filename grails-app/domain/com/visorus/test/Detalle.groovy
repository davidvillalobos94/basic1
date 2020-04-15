package com.visorus.test

class Detalle {

	double precio
	double cantidad
	Articulo articulo

	static belongsTo = [movimiento: Movimiento]

	static constraints = {
		cantidad(min: 1d)
	}
}
