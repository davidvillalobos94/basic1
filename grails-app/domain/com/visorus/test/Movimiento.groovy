package com.visorus.test

class Movimiento {

	double total
	Date fechaVendido

	static hasMany = [detalles: Detalle]

	static constraints = {
	}
}
