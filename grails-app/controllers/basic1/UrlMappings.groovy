package basic1

class UrlMappings {

    static mappings = {

        delete "/$controller/$movimiento(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index") // GET /articulo/ = index()
        get "/$controller/$id(.$format)?"(action:"show") // GET /articulo/1 show()
        post "/$controller(.$format)?"(action:"save") // POST /articulo/ save()
        put "/$controller/$id(.$format)?"(action:"update") // PUT /articulo/1 update()
        patch "/$controller/$id(.$format)?"(action:"patch")

        delete "/$controller/borrar/$id"(action: "borrar")
        get "/movimiento/valida/$id"("controller": "movimiento", action: "valida")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
