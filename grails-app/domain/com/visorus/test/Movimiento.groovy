package com.visorus.test

class Movimiento {

	double total
	Date fechaVendido

	static hasMany = [detalles: Detalle]

	static constraints = {

	}

	Map obtieneDatos() {
		[
				id          : id, version: version,
				total       : total,
				fechaVendido: fechaVendido.format("dd-MM-yyyy"),
				detalles    : detalles*.obtieneDatos()
		]
	}
}
