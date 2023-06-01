from flask import Flask, request, render_template, redirect
from zeep import Client

# URL del archivo WSDL
wsdl_url = 'http://192.168.100.42:8080/PDAP4_web/IOArchivoWebService?WSDL'
# Crear una instancia de Flask
app = Flask(__name__, template_folder='templates')
# Crear un cliente para el servicio web
client = Client(wsdl=wsdl_url)
@app.route('/', methods=['GET', 'POST']) # app.route hace referencia a la ruta de la página
def home():
    archivos = str(client.service.obtenerNombresArchivos())
    archivos = archivos.split('\n')
    if request.method == 'POST': # Obtener los parámetros del formulario
        if 'vocales'in request.form.to_dict().keys():
            nomArchivo = request.form['vocales']
            # Llamar al método del servicio web
            result = client.service.cuentaVocales(nomArchivo)
            result = f"El archivo {nomArchivo} tiene {result} vocales"
            return redirect('/result?resultado=' + result)
        if 'lineas'in request.form.to_dict().keys():
            nomArchivo = request.form['lineas']
            # Llamar al método del servicio web
            result = client.service.cuentaLineas(nomArchivo)
            result = f"El archivo {nomArchivo} tiene {result} lineas"
            return redirect('/result?resultado=' + result)
        if 'escribir'in request.form.to_dict().keys():
            nomArchivo = request.form['escribir']
            texto = str(request.form['texto'])
            # Llamar al método del servicio web
            result = client.service.escribe(nomArchivo, texto)
            if result:
                result = f"El archivo {nomArchivo} ha sido escrito"
                return redirect('/result?resultado=' + result)
            else:
                result = f"El archivo {nomArchivo} no ha sido escrito"
                return redirect('/result?resultado=' + result)
        if 'imprimir'in request.form.to_dict().keys():
            nomArchivo = request.form['imprimir']
            # Llamar al método del servicio web
            result = client.service.imprimir(nomArchivo)
            result = f"El archivo {nomArchivo} Contiene: {result} "
            return redirect('/result?resultado=' + result)
        if 'eliminar'in request.form.to_dict().keys():
            nomArchivo = request.form['eliminar']
            result = client.service.eliminar(nomArchivo)
            if result:
                result = f"El archivo {nomArchivo} ha sido eliminado"
                return redirect('/result?resultado=' + result)
            else:
                result = f"El archivo {nomArchivo} no ha sido eliminado"
                return redirect('/result?resultado=' + result)
        if 'renombrar'in request.form.to_dict().keys():
            nomArchivo = request.form['renombrar']
            nuevoNombre = request.form['nuevoNombre']
            # Llamar al método del servicio web
            result = client.service.renombrar(nomArchivo, nuevoNombre)
            if result:
                result = f"El archivo {nomArchivo} ha sido renombrado a {nuevoNombre}"
                return redirect('/result?resultado=' + result)
            else:
                result = f"El archivo {nomArchivo} no ha sido renombrado a {nuevoNombre}"
                return redirect('/result?resultado=' + result)
        if 'respaldar'in request.form.to_dict().keys():
            nomArchivo = request.form['respaldar']
            # Llamar al método del servicio web
            result = client.service.respaldar(nomArchivo)
            if result:
                result = f"El archivo {nomArchivo} ha sido respaldado"
                return redirect('/result?resultado=' + result)
            else:
                result = f"El archivo {nomArchivo} no ha sido respaldado"
                return redirect('/result?resultado=' + result)
        if 'copiar'in request.form.to_dict().keys():
            nomArchivo = request.form['copiar']
            archDestino = request.form['Acopiar']
            # Llamar al método del servicio web
            result = client.service.copiar(nomArchivo, archDestino)
            if result:
                result = f"El archivo {nomArchivo} ha sido copiado a {archDestino}"
                return redirect('/result?resultado=' + result)
            else:
                result = f"El archivo {nomArchivo} no ha sido copiado a {archDestino}"
                return redirect('/result?resultado=' + result)
            # Mostrar el resultado en la página
    return render_template('index.html', archivos=archivos)
    
@app.route('/result')
def result():
    resultado = request.args.get('resultado')
    return render_template('result.html', resultado=resultado)

if __name__ == '__main__':
    # Ejecutar la aplicación Flask en el puerto 5000
    app.run(port=5000)

    
