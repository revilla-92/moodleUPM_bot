package es.upm.dit.apsv.bot;

import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import jdk.nashorn.internal.ir.ContinueNode;


@SuppressWarnings("unused")
public class MoodleUPM_bot extends TelegramLongPollingBot {

	//Variable que guarda del mensaje anterior que ha enviado el usuario (i-1).
	String ultimoEnvio="No selected";
	String degreeOrMaster="No selected";
	String course = "No selected";
	String semester = "No selected";
	String specialty = "No selected";
	String optative = "No selected";
	String tipeCalendar = "No selected";
	String event = "";
	boolean info = false;
	boolean planEstudios = false;
	boolean curso = false;
	boolean semestre = false;
	boolean especialidad = false;
	boolean optativa = false;
	boolean calendario = false;
	boolean tipoCalendario = false;
	boolean fecha = false;
	boolean materia = false;
	boolean evento = false;
	boolean fechaEvento = false;
	boolean asignaturaEvento = false;
	boolean lugarEvento = false;
	boolean descripcionEvento = false;
	int faseEvento = 0;

	public void limpiaVariables(){
		degreeOrMaster="No selected";
		course="No selected";
		semester="No selected";
		specialty="No selected";
		optative = "No selected";
		tipeCalendar = "No selected";
		event = "";
		info = false;
		planEstudios = false;
		curso = false;
		semestre = false;
		especialidad=false;
		optativa = false;
		calendario = false;
		tipoCalendario = false;
		fecha = false;
		materia = false;
		evento = false;
		fechaEvento = false;
		asignaturaEvento = false;
		lugarEvento = false;
		descripcionEvento = false;
		faseEvento = 0;
	}

	
	public String cleanString(String texto) {
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return texto;
	}
	

	@Override
	public void onUpdateReceived(Update update) {

		String a = "Bievenido a moodleDIT_bot. ¿En qué le puedo ayudar, ";
		String b = "/help --> para acceder a la ayuda de utilización de este bot en cualquier momento.";
		String c = "/info --> realiza una búsqueda guiada de información.";
		String d = "/pregunta --> seguido de su pregunta sobre el funcionamiento de moodle y aspectos generales docentes.";
		String e = "/profesor --> seguido del nombre del profesor sobre el que desea información. La primera letra del nombre y apellidos deben ir escritos en mayúscula.";
		String f = "/asignatura --> seguido del nombre o el acrónimo de la asignatura sobre la que desea información. Si se introduce el nombre deberá poner las primeras letras en mayúscula.";
		String g = "/departamento --> seguido del acrónimo del departamento sobre el que desea información";
		String h = "/calendario --> consulta y creación de eventos de manera guiada.";
		
		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		List<KeyboardRow> keyboard = new ArrayList<>();
		keyboardMarkup.setKeyboard(keyboard);
		
		//Teclado para borrar los teclados
		ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove();
		
		//Teclado que pasamos como variable en el mensaje, y la usamos para borrar el teclado o no.
		ReplyKeyboard tipoKeyboard = keyboardMarkup;


		if (update.hasMessage() && update.getMessage().hasText()){

			String incomingMsg = update.getMessage().getText();
			String name = update.getMessage().getFrom().getFirstName();
			Long incomingMsg_id = update.getMessage().getChatId();
			
			if(incomingMsg.equals("ImagenSonido")){
				incomingMsg = "Imagen";
			}
			
			
			System.out.println("El mensaje que acaba de llegar del usuario " + name + " : " + incomingMsg);
			System.out.println("Ultimo mensaje anterior a " + incomingMsg + " de " + name + " : " + ultimoEnvio);
			String responseMsg;
			String lastMsg;
			
			

			switch(incomingMsg){

			case "/start":
				responseMsg = a + name + "?" + "\n" + b + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n" + g + "\n" + h;
				lastMsg=incomingMsg;
				limpiaVariables();
				tipoKeyboard = keyboardRemove;
				break;

			case "/help":
				responseMsg = "¿En que le puedo ayudar, " + name + "?" + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n" + g + "\n" + h;
				lastMsg=incomingMsg;
				limpiaVariables();
				tipoKeyboard = keyboardRemove;
				break;

			case "/info":
				responseMsg = "Estupendo " + name + ", indiqueme el plan de estudios del que quiere obtener información, por favor.";
				lastMsg=incomingMsg;
				info=true;
				KeyboardRow row1 = new KeyboardRow();
				row1.add("GITST");
				row1.add("MUIT");
				keyboard.add(row1);
				KeyboardRow row2 = new KeyboardRow();
				row2.add("Exit");
				keyboard.add(row2);
				break;

			case "GITST":

				if(info && (planEstudios == false)){
					responseMsg = "Indique el curso, por favor.";
					lastMsg=incomingMsg;
					degreeOrMaster=incomingMsg;
					planEstudios=true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primero");
					row3.add("Segundo");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Tercero");
					row4.add("Cuarto");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Back");
					row5.add("Exit");
					keyboard.add(row5);
					break;
					
				} else {
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another6";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}

			case "MUIT":

				if(info&&(planEstudios==false)){
					responseMsg = "Indique el curso, por favor.";
					lastMsg=incomingMsg;
					degreeOrMaster=incomingMsg;
					planEstudios=true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primero");
					row3.add("Segundo");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Back");
					row4.add("Exit");
					keyboard.add(row4);
					break;
				} else {
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another7";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}

				
			case "Primero":

				if(info && planEstudios && (curso==false)){
					responseMsg = "Indique el semestre, por favor.";
					lastMsg=incomingMsg;
					course = incomingMsg;
					curso = true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primer Semestre");
					row3.add("Segundo Semestre");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Back");
					row4.add("Exit");
					keyboard.add(row4);
					break;
				} else {
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another8";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				
			case "Tercero":

				if(info && planEstudios && (curso==false)){
					responseMsg = "Indique el semestre, por favor.";
					lastMsg=incomingMsg;
					course = incomingMsg;
					curso = true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primer Semestre");
					row3.add("Segundo Semestre");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Optativas");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Back");
					row5.add("Exit");
					keyboard.add(row5);
					break;
				} else {
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another8";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}

			case "Segundo":

				if(info&&planEstudios&&(curso==false)){
					if(degreeOrMaster.equals("MUIT")){
						responseMsg = "Indique la especialidad, por favor.";
						lastMsg=incomingMsg;
						course=incomingMsg;
						curso = true;
						KeyboardRow row3 = new KeyboardRow();
						row3.add("Sistemas");
						row3.add("Telemática");
						keyboard.add(row3);
						KeyboardRow row4 = new KeyboardRow();
						row4.add("Electrónica");
						row4.add("Gestión");
						keyboard.add(row4);
						KeyboardRow row5 = new KeyboardRow();
						row5.add("Bioingeniería");
						keyboard.add(row5);
						KeyboardRow row6 = new KeyboardRow();
						row6.add("Back");
						row6.add("Exit");
						keyboard.add(row6);
						break;
					}else{
						responseMsg = "Indique el semestre, por favor.";
						lastMsg=incomingMsg;
						course=incomingMsg;
						curso = true;
						KeyboardRow row3 = new KeyboardRow();
						row3.add("Primer Semestre");
						row3.add("Segundo Semestre");
						keyboard.add(row3);
						KeyboardRow row4 = new KeyboardRow();
						row4.add("Optativas");
						keyboard.add(row4);
						KeyboardRow row5 = new KeyboardRow();
						row5.add("Back");
						row5.add("Exit");
						keyboard.add(row5);
						break;
					}
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another9";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}

			case "Cuarto":
				
				if(info&&planEstudios&&(curso==false)){
					responseMsg = "Indique la especialidad, por favor.";
					lastMsg=incomingMsg;
					course=incomingMsg;
					curso = true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Sistemas");
					row3.add("Telemática");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Electrónica");
					row4.add("ImagenSonido");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Back");
					row5.add("Exit");
					keyboard.add(row5);
					break;
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another10";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}

			case "Sistemas":
			case "Telemática":
			case "Electrónica":
			case "Gestión":
			case "Bioingeniería":
			
				if(info && planEstudios &&curso && (especialidad==false) && (degreeOrMaster.equals("MUIT") && course.equals ("Segundo"))){
					responseMsg = "Indique el semestre, por favor.";
					lastMsg=incomingMsg;
					specialty=incomingMsg;
					especialidad = true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primer Semestre");
					row3.add("Segundo Semestre");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Back");
					row4.add("Exit");
					keyboard.add(row4);
					break;
				}else if((info && planEstudios &&curso && (especialidad==false) && degreeOrMaster.equals("GITST")&&course.equals("Cuarto"))){
					responseMsg = "Indique el semestre, por favor.";
					lastMsg=incomingMsg;
					specialty=incomingMsg;
					especialidad = true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primer Semestre");
					row3.add("Segundo Semestre");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Optativas");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Back");
					row5.add("Exit");
					keyboard.add(row5);
					break;
				}else {
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another11";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				
			case "Imagen":
				if(info && planEstudios && curso && (especialidad == false) && course.equals("Cuarto")){
					responseMsg = "Indique el semestre, por favor.";
					lastMsg=incomingMsg;
					specialty=incomingMsg;
					especialidad = true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primer Semestre");
					row3.add("Segundo Semestre");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Optativas");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Back");
					row5.add("Exit");
					keyboard.add(row5);
					break;
				}else{
					System.out.println("Entro aqui gilipollas2");
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another14";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				

			case "ImagenSonido":
				if(info && planEstudios && curso && (especialidad == false) && course.equals("Cuarto")){
					responseMsg = "Indique el semestre, por favor.";
					lastMsg=incomingMsg;
					specialty=incomingMsg;
					especialidad = true;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primer Semestre");
					row3.add("Segundo Semestre");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Optativas");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Back");
					row5.add("Exit");
					keyboard.add(row5);
					break;
				}else{
					System.out.println("Entro aqui gilipollas2");
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another14";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				
			case "Primer Semestre":

				if(info&&planEstudios&&curso){
					//Si estas en Muit y Segundo de master y metes Primer semestre sin elegir especialidad te peinas, y en cuarto de grado igual.
					if((degreeOrMaster.equals("MUIT")&&course.equals("Segundo")&&!especialidad)||(course.equals("Cuarto")&&!especialidad)){
						responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
						lastMsg="another12";
						limpiaVariables();
						tipoKeyboard = keyboardRemove;
						break;
					}else{
						
						lastMsg=incomingMsg;
						semester = incomingMsg;
						semestre = true;
						
						// Variables para la busqueda en la BBDD:
						String semestre = "";
						String curso = "";
						String especialidad = cleanString(specialty);
						
						String especialidadDefinitiva = "";
						
						// Traducir selecciones para que la BBDD lo entienda.
						if(semester.equals("Primer Semestre")){
							semestre = "1";
						}else if(semester.equals("Segundo Semestre")){
							semestre = "2";
						}else{
							semestre = "";
						}
						if(course.equals("Primero")){
							curso = "1";
						}else if (course.equals("Segundo")){
							curso = "2";
						}else if (course.equals("Tercero")){
							curso = "3";
						}else if (course.equals("Cuarto")){
							curso = "4";
						}else{
							curso = "";
						}
						if(specialty.equals("No selected")){
							especialidad = "";
						}
						if(especialidad.equals("No selected")){
							especialidadDefinitiva = "";
						}else if(especialidad.equals("Imagen")){
							especialidadDefinitiva = "Imagen y Sonido";
						}else{
							especialidadDefinitiva = especialidad;
						}
						
						System.out.println("Plan estudios: " + degreeOrMaster);
						System.out.println("Curso: " + curso);
						System.out.println("Semestre: " + semestre);
						System.out.println("Especialidad: " + especialidad);
						
	        			// Conecto la BBDD
	        			AsignaturaBD asignaturabd = new AsignaturaBD();

        				// Resultado de la base de datos
        				List<Asignatura> asignaturasRecuperadas = new ArrayList<Asignatura>();
        				
        				// Lanzo la query a la BBDD
            			try {
            				asignaturasRecuperadas = asignaturabd.recuperaAsignaturas(degreeOrMaster, curso, semestre, especialidadDefinitiva);
        					for(Asignatura i : asignaturasRecuperadas){
        						System.out.println(i.toString());
        					}
        				} catch (SQLException e1) {
        					e1.printStackTrace();
        				}
            			
            			// Mando el mensaje de respuesta
            			if(asignaturasRecuperadas.isEmpty()){
            				responseMsg = "No hay ninguna asignatura con las selecciones escogidas";
            			}else{
            				String res = "Se han encontrado las siguientes asignaturas: \n \n";
            				for(Asignatura i : asignaturasRecuperadas){
            					res += i.toString() + "\n \n";
            				}
            				responseMsg = res;
            			}
						break;
					}
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another13";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}

			case "Segundo Semestre":

				if(info&&planEstudios&&curso){
					if((degreeOrMaster.equals("MUIT")&&course.equals("Segundo")&&!especialidad)||(course.equals("Cuarto")&&!especialidad)){
						responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
						lastMsg="another1";
						limpiaVariables();
						tipoKeyboard = keyboardRemove;
						break;
					}else{
						
						
						lastMsg=incomingMsg;
						semester = incomingMsg;
						semestre = true;
						
						// Variables para la busqueda en la BBDD:
						String semestre = "";
						String curso = "";
						String especialidad = cleanString(specialty);
						
						String especialidadDefinitiva = "";
						
						// Traducir selecciones para que la BBDD lo entienda.
						if(semester.equals("Primer Semestre")){
							semestre = "1";
						}else if(semester.equals("Segundo Semestre")){
							semestre = "2";
						}else{
							semestre = "";
						}
						if(course.equals("Primero")){
							curso = "1";
						}else if (course.equals("Segundo")){
							curso = "2";
						}else if (course.equals("Tercero")){
							curso = "3";
						}else if (course.equals("Cuarto")){
							curso = "4";
						}else{
							curso = "";
						}
						if(especialidad.equals("No selected")){
							especialidadDefinitiva = "";
						}else if(especialidad.equals("Imagen")){
							especialidadDefinitiva = "Imagen y Sonido";
						}else{
							especialidadDefinitiva = especialidad;
						}
						
						System.out.println("Plan estudios: " + degreeOrMaster);
						System.out.println("Curso: " + curso);
						System.out.println("Semestre: " + semestre);
						System.out.println("Especialidad: " + especialidad);
						
	        			// Conecto la BBDD
	        			AsignaturaBD asignaturabd = new AsignaturaBD();

        				// Resultado de la base de datos
        				List<Asignatura> asignaturasRecuperadas = new ArrayList<Asignatura>();
        				
        				// Lanzo la query a la BBDD
            			try {
            				asignaturasRecuperadas = asignaturabd.recuperaAsignaturas(degreeOrMaster, curso, semestre, especialidadDefinitiva);
        					for(Asignatura i : asignaturasRecuperadas){
        						System.out.println(i.toString());
        					}
        				} catch (SQLException e1) {
        					e1.printStackTrace();
        				}
            			
            			// Mando el mensaje de respuesta
            			if(asignaturasRecuperadas.isEmpty()){
            				responseMsg = "No hay ninguna asignatura con las selecciones escogidas";
            			}else{
            				String res = "Se han encontrado las siguientes asignaturas: \n \n";
            				for(Asignatura i : asignaturasRecuperadas){
            					res += i.toString() + "\n \n";
            				}
            				responseMsg = res;
            			}
						
						break;
					}
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another2";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				
			case "Optativas":
				
				if(info&&planEstudios&&curso&&(optativa==false)){
					
					if(degreeOrMaster.equals("GITST")&&(course.equals("Primero")==false)){

						lastMsg=incomingMsg;
						semester = incomingMsg;
						semestre = true;
						
						// Variables para la busqueda en la BBDD:
						String semestre = "";
						String curso = "";
						String especialidad = cleanString(specialty);
						
						// Traducir selecciones para que la BBDD lo entienda.
						if(semester.equals("Primer Semestre")){
							semestre = "1";
						}else if(semester.equals("Segundo Semestre")){
							semestre = "2";
						}else{
							semestre = "";
						}
						if(course.equals("Primero")){
							curso = "1";
						}else if (course.equals("Segundo")){
							curso = "2";
						}else if (course.equals("Tercero")){
							curso = "3";
						}else if (course.equals("Cuarto")){
							curso = "4";
						}else{
							curso = "";
						}
						if(specialty.equals("No selected")){
							especialidad = "";
						}
						
						System.out.println("Plan estudios: " + degreeOrMaster);
						System.out.println("Curso: " + curso);
						System.out.println("Semestre: " + semestre);
						System.out.println("Especialidad: " + especialidad);
						
	        			// Conecto la BBDD
	        			AsignaturaBD asignaturabd = new AsignaturaBD();

        				// Resultado de la base de datos
        				List<Asignatura> asignaturasRecuperadas = new ArrayList<Asignatura>();
        				
        				// Lanzo la query a la BBDD
            			try {
            				asignaturasRecuperadas = asignaturabd.recuperaOptativas(degreeOrMaster, curso);
        					for(Asignatura i : asignaturasRecuperadas){
        						System.out.println(i.toString());
        					}
        				} catch (SQLException e1) {
        					e1.printStackTrace();
        				}
            			
            			// Mando el mensaje de respuesta
            			if(asignaturasRecuperadas.isEmpty()){
            				responseMsg = "No hay ninguna asignatura con las selecciones escogidas";
            			}else{
            				String res = "Se han encontrado las siguientes asignaturas: \n \n";
            				for(Asignatura i : asignaturasRecuperadas){
            					res += i.toStringCorto() + "\n \n";
            				}
            				responseMsg = res;
            			}
						
						break;
					}else{
						responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
						lastMsg="another";
						limpiaVariables();
						tipoKeyboard = keyboardRemove;
						break;
					}
				
				}
				
			case "/calendario":
				
				responseMsg = "Estupendo " + name + ", seleccione un calendario.";
				lastMsg=incomingMsg;
				calendario=true;
				KeyboardRow row7 = new KeyboardRow();
				row7.add("Oficial");
				row7.add("Alumnos");
				keyboard.add(row7);
				KeyboardRow row8 = new KeyboardRow();
				row8.add("Exit");
				keyboard.add(row8);
				break;
				
			case "Oficial":
				if(calendario){
					responseMsg = "Estupendo " + name + ", seleccione un calendario.";
					lastMsg=incomingMsg;
					tipoCalendario=true;
					tipeCalendar = "Oficial";
					KeyboardRow row9 = new KeyboardRow();
					row9.add("Fecha");
					row9.add("Materia");
					keyboard.add(row9);
					KeyboardRow row10 = new KeyboardRow();
					row10.add("Back");
					row10.add("Exit");
					keyboard.add(row10);
					break;
					
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another18";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				
				
			case "Alumnos":
				if(calendario){
					responseMsg = "Estupendo " + name + ", seleccione un calendario.";
					lastMsg=incomingMsg;
					tipoCalendario=true;
					tipeCalendar = "Alumnos";
					KeyboardRow row9 = new KeyboardRow();
					row9.add("Fecha");
					row9.add("Materia");
					row9.add("CrearEvento");
					keyboard.add(row9);
					KeyboardRow row10 = new KeyboardRow();
					row10.add("Back");
					row10.add("Exit");
					keyboard.add(row10);
					break;
					
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another19";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				
				
			case "Fecha":
				
				if(calendario && tipoCalendario){
					responseMsg = "Introduce una fecha con el formato DD/MM/AAAA: ";
					lastMsg=incomingMsg;
					fecha = true;
					tipoKeyboard = keyboardRemove;
					break;
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another20";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				
				
			case "Materia":
				
				if(calendario && tipoCalendario){
					responseMsg = "Introduce el acronimo de una asignatura: ";
					lastMsg=incomingMsg;
					materia = true;
					tipoKeyboard = keyboardRemove;
					break;
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another21";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				
				
			case "CrearEvento":
				
				if(calendario && tipoCalendario){
					responseMsg = "A continuación va a crear un evento, por favor siga las instrucciones. "
							+ "Introduzca la fecha en formato DD/MM/AAAA seguido opcionalmente de la hora: ";
					lastMsg=incomingMsg;
					evento = true;
					tipoKeyboard = keyboardRemove;
					break;
				}else{
					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another21";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					break;
				}
				

			case "Back":
				
				if(calendario){
					tipoCalendario = false;
					responseMsg = "Estupendo " + name + ", seleccione un calendario.";
					lastMsg=incomingMsg;
					calendario=true;
					KeyboardRow row9 = new KeyboardRow();
					row9.add("Oficial");
					row9.add("Alumnos");
					keyboard.add(row9);
					KeyboardRow row10 = new KeyboardRow();
					row10.add("Exit");
					keyboard.add(row10);
					break;
				}
				
				if(especialidad && degreeOrMaster.equals("MUIT")){
					if(semestre){
						semestre = false;
						semester = "No selected";
					}
					especialidad=false;
					specialty="No selected";
					responseMsg = "Indique la especialidad, por favor.";
					lastMsg=incomingMsg;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Sistemas");
					row3.add("Telemática");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Electrónica");
					row4.add("Gestión");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Bioingeniería");
					keyboard.add(row5);
					KeyboardRow row6 = new KeyboardRow();
					row6.add("Back");
					row6.add("Exit");
					keyboard.add(row6);
					break;
				}else if(especialidad&&degreeOrMaster.equals("GITST")){ 
					if(semestre){
						semestre = false;
						semester = "No selected";
					}
					especialidad=false;
					specialty="No selected";
					responseMsg = "Indique la especialidad, por favor.";
					lastMsg=incomingMsg;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Sistemas");
					row3.add("Telemática");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Electrónica");
					row4.add("ImagenSonido");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Back");
					row5.add("Exit");
					keyboard.add(row5);
					break;

				}else if (curso&&degreeOrMaster.equals("GITST")){
					if(semestre){
						semestre = false;
						semester = "No selected";
					}
					curso = false;
					course = "No selected";
					responseMsg = "Indique el curso, por favor.";
					lastMsg=incomingMsg;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primero");
					row3.add("Segundo");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Tercero");
					row4.add("Cuarto");
					keyboard.add(row4);
					KeyboardRow row5 = new KeyboardRow();
					row5.add("Back");
					row5.add("Exit");
					keyboard.add(row5);
					break;
				} else if(curso&&degreeOrMaster.equals("MUIT")){
					if(semestre){
						semestre = false;
						semester = "No selected";
					}
					curso=false;
					course="No selected";
					responseMsg = "Indique el curso, por favor.";
					lastMsg=incomingMsg;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("Primero");
					row3.add("Segundo");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Back");
					row4.add("Exit");
					keyboard.add(row4);
					break;
				} else {

					planEstudios=false;
					degreeOrMaster="No selected";
					responseMsg = "Indiqueme el plan de estudios del que quiere obtener información, por favor.";
					lastMsg=incomingMsg;
					KeyboardRow row3 = new KeyboardRow();
					row3.add("GITST");
					row3.add("MUIT");
					keyboard.add(row3);
					KeyboardRow row4 = new KeyboardRow();
					row4.add("Exit");
					keyboard.add(row4);
					break;
				}

			case "Exit":
				
				responseMsg = "Hola de nuevo, " + name + ". ¿En qué le puedo ayudar? " + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n" + g + "\n" + h;
				lastMsg="No selected";
				limpiaVariables();
				tipoKeyboard = keyboardRemove;
				break;


			default:
				
				responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
				lastMsg="another3";
				
				if(incomingMsg.substring(0,1).equals("/")){
					
					
					// Comprobamos la longitud del mensaje
					int length = incomingMsg.length();
					if(length > 8){
						
						// Implementacion de /asignatura
						if(length >= 11 && incomingMsg.substring(1, 11).toLowerCase().equals("asignatura")){
							
							// Cogemos el acronimo o el nombre
							String asignatura = incomingMsg.substring(11).trim();
							System.out.println("La asignatura nombre o acronimo es: " + asignatura); 
		        			lastMsg=incomingMsg;
		        			
		        			// Conecto la BBDD
		        			AsignaturaBD asignaturabd = new AsignaturaBD();
		        			ProfesorBD profesorbd = new ProfesorBD();
		        			
		        			// Si se trata del nombre de una asignatura
		        			if(asignatura.length()>5){
		        				
		        				// Traza para ver el nombre recuperado
		        				System.out.println("El nombre de la asignatura introducida es: " + cleanString(asignatura));
		            			
		        				// Resultado a devolver
		        				List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		        				
		        				// Lanzo la query a la BBDD
		            			try {
		            				asignaturas = asignaturabd.recuperarPorNombre(cleanString(asignatura));
		        					System.out.println(asignaturas.toString());
		        				} catch (SQLException e1) {
		        					e1.printStackTrace();
		        				}
		            			
		            			// Mando el mensaje de respuesta
		            			if(asignaturas.isEmpty()){
		            				responseMsg = "No hay ninguna asignatura con el nombre introducido";
		            			}else{
		            				String res = "Las asignaturas recuperadas por ese nombre son las siguientes, si desea"
		            						+ " más información sobre la asignatura use /asignatura seguida de su acronimo: \n \n ";
		            				for(Asignatura i: asignaturas){
		            					res += i.toString() + " \n \n";
		            				}
		            				responseMsg = res;
		            			}
		            			
		            			
		            		// Si se trata del acronimo de una asignatura	
		        			}else {
		        				
		        				// Traza para ver el acronimo recuperado
		        				System.out.println("El nombre de la asignatura introducida es: " + cleanString(asignatura));
		            			
		        				// Resultado a devolver inicializado a null
		        				Asignatura asignaturaRecuperada = null;
		        				List<Profesor> profesores = new ArrayList<Profesor>();
		        				
		        				// Lanzo la query a la BBDD
		            			try {
		        					asignaturaRecuperada = asignaturabd.recuperarPorAcronimo(cleanString(asignatura.toUpperCase()));
		        					profesores = profesorbd.recuperarPorAsignaturaAcronimo(cleanString(asignatura.toUpperCase()));
		        					System.out.println(asignaturaRecuperada.toString());
		        				} catch (SQLException e1) {
		        					e1.printStackTrace();
		        				}
		            			
		            			// Mando el mensaje de respuesta
		            			if(asignaturaRecuperada == null){
		            				responseMsg = "No hay ninguna asignatura con el nombre o acronimo introducido";
		            			}else{
		            				String res = asignaturaRecuperada.toString() + " \n \n Los profesores que imparten dicha asignatura son: ";
		            				for(Profesor i : profesores){
		            					res += "\n \n " + i.toString();
		            				}
		            				responseMsg = res + "\n"; 
		            			}
		        				
		        			}
							
		        		
		        		// Implementación de /pregunta
						}else if(length >= 9 && incomingMsg.substring(1, 9).toLowerCase().equals("pregunta")){
							
							// Cogemos la pregunta
							String pregunta = cleanString(incomingMsg.substring(9).trim().toLowerCase());
							System.out.println("La pregunta es: " + pregunta); 
		        			lastMsg=incomingMsg;
							
		        			// Conecto la BBDD
		        			FAQBD faqbd = new FAQBD();
		        			
		        			// Dividimos las palabras de la pregunta
		        			String [] palabras = pregunta.split(" ");

		        			// Inicializamos ws, accionprincipal y accionsecundaria
		        			List<String> ws = new ArrayList<String>();
		        			List<String> accionPrincipal = new ArrayList<String>();
		        			List<String> accionSecundaria = new ArrayList<String>();
		        			
		        			// Recuperamos los posibles valores de ws, accionprincipal y accionsecundaria
		        			try {
								ws = faqbd.recuperaWS();
								accionPrincipal = faqbd.recuperaAccionPrincipal();
								accionSecundaria = faqbd.recuperaAccionSecundaria();
							} catch (SQLException e1) {
								System.out.println("Me he pegado una castaña");
								e1.printStackTrace();
							}
		        			
		        			// Variables donde recuperar de la pregunta la ws, accionprimaria y accionsecundaria
		        			String wsPregunta = "";
		        			String accionPrincipalPregunta = "";
		        			String accionSecundariaPregunta = "";
		        			
		        			// Comprobamos si alguna de las palabras de la pregunta coincide con las listas anteriores
		        			for(int j = 0; j < palabras.length; j++){
		        				for(String i : ws){
		        			    	if(i.equals(palabras[j])){
		        			    		wsPregunta = i;
		        			    	}
		        			    }
		        			} 
		        			
		        			for(int j = 0; j < palabras.length; j++){
		        				for(String i : accionPrincipal){
		        			    	if(i.equals(palabras[j])){
		        			    		accionPrincipalPregunta = i;
		        			    	}
		        			    }
		        			} 
		        			
		        			for(int j = 0; j < palabras.length; j++){
		        				for(String i : accionSecundaria){
		        			    	if(i.equals(palabras[j])){
		        			    		accionSecundariaPregunta = i;
		        			    	}
		        			    }
		        			} 
		        			
		        			// Imprimimos si hay coincidencias en la pregunta
		        			System.out.println("La WS de la pregunta introducida es: " + wsPregunta);
		        			System.out.println("La AccionPrincipal de la pregunta introducida es: " + accionPrincipalPregunta);
		        			System.out.println("La AccionSecundaria de la pregunta introducida es: " + accionSecundariaPregunta);
							
		        			
		        			// Variable donde recoger la respuesta a la pregunta
		        			String respuesta = "";
		        			
		        			// Finalmente buscamos en la BBDD.
		        			try {
		        				respuesta = faqbd.recuperarRespuesta(wsPregunta, accionPrincipalPregunta, accionSecundariaPregunta);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
		        			
		        			// Mandamos el mensaje en función de la respuesta.
		        			if(respuesta.equals("")){
		        				responseMsg = "Lo lamento su pregunta no ha sido formulada correctamente.";
		        			}else{
		        				responseMsg = "La respuesta a: " + pregunta + " es: " + respuesta;
		        			}
				
						}
						
						// Implementación de /profesor
						else if(length >= 9 && incomingMsg.substring(1, 9).toLowerCase().equals("profesor")){
								
							// Cogemos el nombre del profesor
							String profesor = incomingMsg.substring(9).trim();
							System.out.println("La asignatura nombre o acronimo es: " + profesor); 
		        			lastMsg=incomingMsg;
		        			
		        			// Conecto la BBDD
		        			ProfesorBD profesorbd = new ProfesorBD();
		        			
		        			// Traza para ver el nombre recuperado
	        				System.out.println("El nombre del profesor introducido es: " + cleanString(profesor));
	            			
	        				// Resultado de la base de datos
	        				List<String> profesoresRecuperados = new ArrayList<String>();
	        				
	        				
	        				// Lanzo la query a la BBDD
	            			try {
	        					profesoresRecuperados = profesorbd.recuperarPorNombre(profesor);
	        					for(String i : profesoresRecuperados){
	        						System.out.println(i);
	        					}
	        				} catch (SQLException e1) {
	        					e1.printStackTrace();
	        				}
	            			
	            			// Mando el mensaje de respuesta
	            			if(profesoresRecuperados.isEmpty()){
	            				responseMsg = "No hay ningún profesor con el nombre introducido";
	            			}else{
	            				String res = "Se han encontrado los siguientes profesores: \n \n";
	            				for(String i : profesoresRecuperados){
	            					res += i + "\n \n";
	            					System.out.println(res);
	            				}
	            				responseMsg = res;
	            			}
						}
						
						// Implementacion de /departamento
						else if(length >= 13 && incomingMsg.substring(1, 13).toLowerCase().equals("departamento")){
							
							// Cogemos el acronimo del departamento
							String departamento = incomingMsg.substring(13).trim().toUpperCase();
							System.out.println("El acronimo del departamento es: " + departamento); 
		        			lastMsg=incomingMsg;
		        			
		        			// Conecto la BBDD
		        			DepartamentoBD departamentobd = new DepartamentoBD();
		        			
		        			// Traza para ver el nombre recuperado
	        				System.out.println("El acronimo del departamento introducido es: " + cleanString(departamento));
	            			
	        				// Resultado a devolver
	        				Departamento departamentoRecuperado = null;
	        				
	        				// Lanzo la query a la BBDD
	            			try {
	            				departamentoRecuperado = departamentobd.recuperarPorAcronimo(cleanString(departamento));
	        					System.out.println("Se trata del " + departamentoRecuperado.toString());
	        				} catch (SQLException e1) {
	        					e1.printStackTrace();
	        				}
	            			
	            			// Mando el mensaje de respuesta
	            			if(departamentoRecuperado == null){
	            				responseMsg = "No hay ningún departamento con el acronimo introducido";
	            			}else{
	            				responseMsg = "Se refiere al " + departamentoRecuperado.toString();
	            			}
						}
						
					// Implementación de mensaje por defecto cuando no es reconocido	
					}else{
						
						responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
						lastMsg="another4";
						limpiaVariables();
						tipoKeyboard = keyboardRemove;
						
					}
					
				
				// Implementación de buscar en BBDD del calendario por FECHA
				}else if(calendario && tipoCalendario && fecha){
					
					// Recogemos el mensaje que debe de ser una fecha.
					String fecha = incomingMsg;
					
					// Inicializamos la BBDD del Calendario
					CalendarioBD calendariobd = new CalendarioBD();
					
					// Crea una lista con los eventos en dicha fecha(s)
					List<Calendario> eventosRecuperados = new ArrayList<Calendario>();
					
					// Lanzamos una query u otra en función del tipo del calendario.
					if(tipeCalendar.equals("Oficial")){
						try {
							eventosRecuperados = calendariobd.recuperarEventosPorFechaOficial(fecha);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}else if(tipeCalendar.equals("Alumnos")){
						try {
							eventosRecuperados = calendariobd.recuperarEventosPorFechaAlumnos(fecha);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}else{
						responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
						lastMsg="another24";
					}
					
					// Reiniciamos las variables
					limpiaVariables();
					
					String res = "";
					
					// Genero el mensaje de respuesta de la BBDD.
					if(eventosRecuperados.isEmpty()){
						responseMsg = "En la fecha que ha introducido aún no hay creado ningún evento";
					}else{
						res = "Se han encontrado los siguientes eventos: \n \n";
        				for(Calendario i : eventosRecuperados){
        					res += i + "\n \n";
        				}
					}
					responseMsg = res;
				}
				
				
				// Implementación de buscar en BBDD del calendario por ASIGNATURA
				else if (calendario && tipoCalendario && materia){
					
					// Recogemos el mensaje que debe de ser una fecha.
					String asignatura = incomingMsg.toUpperCase();
					
					System.out.println("La asignatura pedida es:" + asignatura);
					
					// Inicializamos la BBDD del Calendario
					CalendarioBD calendariobd = new CalendarioBD();
					
					// Crea una lista con los eventos en dicha fecha(s)
					List<Calendario> eventosRecuperados = new ArrayList<Calendario>();
					
					// Lanzamos una query u otra en función del tipo del calendario.
					if(tipeCalendar.equals("Oficial")){
						try {
							eventosRecuperados = calendariobd.recuperarEventosPorAsignaturaOficial(asignatura);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}else if(tipeCalendar.equals("Alumnos")){
						try {
							eventosRecuperados = calendariobd.recuperarEventosPorAsignaturaAlumnos(asignatura);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}else{
						responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
						lastMsg="another25";
					}
					
					// Reiniciamos las variables
					limpiaVariables();
					
					String res = "";
					
					// Genero el mensaje de respuesta de la BBDD.
					if(eventosRecuperados.isEmpty()){
						responseMsg = "En la fecha que ha introducido aún no hay creado ningún evento";
					}else{
						res = "Se han encontrado los siguientes eventos: \n \n";
        				for(Calendario i : eventosRecuperados){
        					res += i + "\n \n";
        				}
					}
					responseMsg = res;
				
					
				// Implementacion de CREAR UN EVENTO en la BBDD de alumnos.
				}else if(calendario && tipoCalendario && evento){
					
					fechaEvento = true;
					
					// Las distintas fases de crear un evento.
					if(fechaEvento && asignaturaEvento && lugarEvento && descripcionEvento && faseEvento == 3){
						System.out.println("Hemos llegado al ultimo paso ahora toca crear el evento en la BBDD y reiniciar las variables.");
						
						event += incomingMsg + ";";
						
						System.out.println(event);
						String[] evento = event.split(";");
						
						for(int i = 0; i < evento.length; i++){
							System.out.println("En evento["+i+"] = " + evento[i]);
						}
						
						// Conecto la BBDD
						CalendarioBD calendariobd = new CalendarioBD();
						
						// Al final limpio variables y devuelvo un mensaje de que se ha creado correctamente
						limpiaVariables();
						
						try {
							if(calendariobd.createEventoAlumnos(evento[0], evento[1], evento[2], evento[3], name)){
								responseMsg = "El evento se ha creado correctamente.";
							}else{
								responseMsg = "Se ha producido un error inesperado por favor intentelo de nuevo";
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}else if(fechaEvento && asignaturaEvento && lugarEvento && faseEvento == 2){
						responseMsg = "Deje una descripción del evento";
						descripcionEvento = true;
						faseEvento = 3;
						event += incomingMsg + ";";
						
					}else if(fechaEvento && asignaturaEvento && faseEvento == 1){
						responseMsg = "¿En dónde se va a celebrar dicho evento?";
						lugarEvento = true;
						faseEvento = 2;
						event += incomingMsg.toUpperCase() + ";";
					
					}else if(fechaEvento && faseEvento == 0){
						responseMsg = "¿De qué asignatura se trata? Por favor indiquenos el acronimo";
						asignaturaEvento = true;
						faseEvento = 1;
						event += incomingMsg + ";";
						
					}else{
						responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
						lastMsg="another29";
					}
				}
				
				// Implementación de mensaje por defecto cuando no es reconocido
				else{

					responseMsg = "La opción que ha introducido no está contemplada, por favor, siga las instrucciones";
					lastMsg="another5";
					limpiaVariables();
					tipoKeyboard = keyboardRemove;
					
				}
				
			}
			
			SendMessage outgoingMsg = new SendMessage().setChatId(incomingMsg_id).setText(responseMsg).setReplyMarkup(tipoKeyboard);
			try {
				sendMessage(outgoingMsg); // Call method to send the message
			} catch (TelegramApiException e1) {
				e1.printStackTrace();
			}
			
			//No puede ser incommingMsg porque si envía un GITST que no sea despues de info, podria vacilar.
			ultimoEnvio=lastMsg;
			System.out.println("El usuario " + name + " selecciono el plan de estudios " + degreeOrMaster + " y curso " + course + " y especialidad " + specialty +" y semestre " + semester);
			System.out.println(lastMsg);
			System.out.println();
		}

	}

	@Override
	public String getBotUsername() {
		return "";
	}

	@Override
	public String getBotToken() {
		return "";
	}

}