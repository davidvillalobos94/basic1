package basic1

import com.visorus.test.Articulo
import com.visorus.test.Detalle
import com.visorus.test.Movimiento
import org.grails.datastore.mapping.query.api.BuildableCriteria

class BootStrap {

	def init = { servletContext ->


		Articulo articuloInstance = new Articulo()
		articuloInstance.clave = "0001"
		articuloInstance.nombre = "Mouse"
		articuloInstance.descripcion = "Mouse Logitech"
		articuloInstance.precio = 800d
		articuloInstance.fechaAlta = new Date()
		articuloInstance.activo = true
		println "save: " + articuloInstance.save()

		articuloInstance = new Articulo()
		articuloInstance.clave = "0002"
		articuloInstance.nombre = "Teclado"
		articuloInstance.descripcion = "Teclado logitech"
		articuloInstance.precio = 2000d
		articuloInstance.fechaAlta = new Date()
		articuloInstance.activo = true
		println "save: " + articuloInstance.save()

		articuloInstance = new Articulo()
		articuloInstance.clave = "0003"
		articuloInstance.nombre = "Monitor"
		articuloInstance.descripcion = "Monitor full hd 27\""
		articuloInstance.precio = 3000d
		articuloInstance.fechaAlta = new Date()
		articuloInstance.activo = true
		println "save: " + articuloInstance.save()

		articuloInstance = Articulo.get(1)
		articuloInstance.nombre = "Mouse edicion 2"
		"davidvilla@visorus.com"

		articuloInstance.validate()
		articuloInstance.save(flush: true)

		Articulo articuloInstance2 = Articulo.get(2)


//		articuloInstance2.activo = false
//		articuloInstance2.save(flush: true)


		Articulo articuloInstance3 = Articulo.get(3)
		articuloInstance3.delete(flush: true)

		Movimiento movimiento = new Movimiento()

		Detalle detalle1Instance = new Detalle()
		detalle1Instance.articulo = articuloInstance
		detalle1Instance.cantidad = 3d
		detalle1Instance.precio = articuloInstance.precio
		detalle1Instance.movimiento = movimiento

		Detalle detalle2Instance = new Detalle()
		detalle2Instance.articulo = articuloInstance2
		detalle2Instance.cantidad = 1d
		detalle2Instance.precio = articuloInstance2.precio
		detalle2Instance.movimiento = movimiento

		// movimiento agrega a detalles
		movimiento.addToDetalles(detalle1Instance)
		movimiento.addToDetalles(detalle2Instance)
		movimiento.total = 1000d
		movimiento.fechaVendido = new Date()
		println "mov validate: " + movimiento.validate()
		println "mov errors: " + movimiento.errors
		println "mov save: " + movimiento.save()

//		Movimiento movimiento1 = Movimiento.get(1)
//		println "total: " + movimiento1.total
//		println "detalles: " + movimiento1.detalles


	}
	def destroy = {
	}
}
