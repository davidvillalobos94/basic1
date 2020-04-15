package com.visorus.test

class MovimientoController {
	static responseFormats = ['json', 'xml']

	ArticuloService articuloService

	def index() {
		respond(Movimiento.list())
	}

	def show(long id) {
		Movimiento mmovimiento = Movimiento.get(id)
		respond(mmovimiento)
	}

	def save() {
		Movimiento movimiento = new Movimiento()
		movimiento.total = 0
		movimiento.fechaVendido = new Date()

		List detalles = request.JSON.detalles
		println "detalles: $detalles"
		for (int i = 0; i < detalles.size(); i++) {
			Detalle detalle = new Detalle()
			detalle.cantidad = detalles[i].cantidad
			if (detalles[i]?.articulo instanceof Integer) {
				detalle.articulo = articuloService.get(detalles[i]?.articulo)
			} else {
				detalle.articulo = articuloService.get(detalles[i].articulo.id as long)
			}
			if (detalle.articulo != null) {
				detalle.precio = detalle.articulo.precio
				movimiento.total += detalle.cantidad * detalle.precio
				detalle.movimiento = movimiento
				movimiento.addToDetalles(detalle)
			}
		}
		println "validate: " + movimiento.validate()
		println "errors: " + movimiento.errors
		println "save: " + movimiento.save(flush: true)
//		movimiento.detalles.forEach{ Detalle d ->
//			println d.properties
//		}
		println "movimiento.datos: " + movimiento.obtieneDatos()
		respond(movimiento.obtieneDatos())
	}

	def update(long id) {
		Movimiento movimiento = Movimiento.get(id)
		movimiento.total = 0

		List detalles = request.JSON.detalles
		println "detalles: $detalles"
		for (int i = 0; i < detalles.size(); i++) {
			Detalle detalle
			if (detalles[i].id)
				detalle = Detalle.get(detalles[i].id)
			else
				detalle = new Detalle()

			detalle.cantidad = detalles[i].cantidad
			if (detalles[i]?.articulo instanceof Integer) {
				detalle.articulo = Articulo.get(detalles[i].articulo as long)
			} else {
				detalle.articulo = Articulo.get(detalles[i].articulo.id as long)
			}
			if (detalle.articulo != null) {
				detalle.precio = detalle.articulo.precio
				movimiento.total += detalle.cantidad * detalle.precio

				if (detalles[i].id == null) { // el id es nuevo
					detalle.movimiento = movimiento // le decimos al detalle a que movimiento pertecene
					movimiento.addToDetalles(detalle) // al movimiento le agregamos un nuevo detalle
				}
			}
		}
		println "validate: " + movimiento.validate()
		println "errors: " + movimiento.errors
		println "update: " + movimiento.save(flush: true)
//		movimiento.detalles.forEach{ Detalle d ->
//			println d.properties
//		}
		println "movimiento.datos: " + movimiento.obtieneDatos()
		respond(movimiento.obtieneDatos())
	}

	def delete(long movimiento) {
		Movimiento movimientoInstance = Movimiento.get(movimiento)
		movimientoInstance.delete(flush: true)
		respond(status: 200)
	}

	def borrar(long id) {
		Movimiento movimientoInstance = Movimiento.get(id)
		movimientoInstance.delete(flush: true)
		respond(status: 200)
	}

}
