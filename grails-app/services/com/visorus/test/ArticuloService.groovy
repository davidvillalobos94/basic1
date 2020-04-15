package com.visorus.test

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONObject

import java.text.SimpleDateFormat

@Transactional
class ArticuloService {


    List<Articulo> list() {
        return Articulo.list()
    }

    Articulo get(Long id) {
        Articulo articulo = Articulo.get(id)
        if(!articulo.activo) {
            return null
        }
//        if(articulo.usuario != obtenerUsuarioActual()) { // ejemplo
//            return null
//        }
        return articulo
    }

    Articulo create(Map json) {
        Articulo articulo = new Articulo()
        build(articulo, json)
        articulo.save(flush: true)
    }

    Articulo update(long id, Map json) {
        Articulo articulo = get(id)
        build(articulo, json)
        articulo.save(flush: true)
    }

    Articulo build(Articulo articuloInstance, Map json) {
        articuloInstance.clave = json.clave
        articuloInstance.nombre = json.nombre
        articuloInstance.descripcion = json.descripcion
        articuloInstance.precio = json.precio

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy") // 01-01-2020
        articuloInstance.fechaAlta = sdf.parse(json.fechaAlta)

        articuloInstance.activo = json.activo
        return articuloInstance
    }
}
