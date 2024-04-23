import Carrera.CarreraDAO;
import Carrera.CarreraDTO;
import Estudiante_Tiene_Carrera.Estudiante_Tiene_CarreraDAO;
import Estudiante_Tiene_Carrera.Estudiante_Tiene_CarreraDTO;
import Estudiante_Tiene_Materia.Estudiante_Tiene_MateriaDAO;
import Estudiante_Tiene_Materia.Estudiante_Tiene_MateriaDTO;
import Estudiantes.EstudianteDAO;
import Estudiantes.EstudianteDTO;
import Materia.MateriaDAO;
import Materia.MateriaDTO;
import Notas.NotasDAO;
import Notas.NotasDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InterfazGrafica extends JFrame {
    private EstudianteDAO estudianteDAO = new EstudianteDAO();
    private MateriaDAO materiaDAO = new MateriaDAO();
    private CarreraDAO carreraDAO = new CarreraDAO();
    private NotasDAO notasDAO = new NotasDAO();
    private Estudiante_Tiene_CarreraDAO estudianteTieneCarreraDAO = new Estudiante_Tiene_CarreraDAO();
    private Estudiante_Tiene_MateriaDAO estudianteTieneMateriaDAO = new Estudiante_Tiene_MateriaDAO();
    private JComboBox<String> verTodosComboBox;

    private JComboBox<String> entidadComboBox;
    private JComboBox<String> operacionComboBox;
    private JTextArea resultadoTextArea;
    private JButton btnSalir;
    private JButton btnConsultarMateria;
    private JButton btnCalcularPromedio;
    private JButton btnMateriaNotas;

    public InterfazGrafica() {
        setTitle("Aplicación de Gestión de Datos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        entidadComboBox = new JComboBox<>(new String[]{"Estudiante", "Materia", "Carrera", "Notas", "Estudiante_Tiene_Carrera", "Estudiante_Tiene_Materia"});
        entidadComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entidadSeleccionada = (String) entidadComboBox.getSelectedItem();
                switch (entidadSeleccionada) {
                    case "Estudiantes":
                        cargarOperaciones(new String[]{"SELECCIONAR", "Buscar", "Insertar", "Actualizar", "Eliminar"});
                        break;
                    case "Materias":
                        cargarOperaciones(new String[]{"SELECCIONAR", "Buscar", "Insertar", "Actualizar", "Eliminar"});
                        break;
                    case "Carrera":
                        cargarOperaciones(new String[]{"SELECCIONAR", "Buscar", "Insertar", "Actualizar", "Eliminar"});
                        break;
                    case "Notas":
                        cargarOperaciones(new String[]{"SELECCIONAR", "Buscar", "Insertar", "Actualizar", "Eliminar"});
                        break;
                    case "Estudiante_Tiene_Carrera":
                        cargarOperaciones(new String[]{"SELECCIONAR", "Buscar", "Insertar", "Actualizar", "Eliminar"});
                        break;
                    case "Estudiante_Tiene_Materia":
                        cargarOperaciones(new String[]{"SELECCIONAR", "Buscar", "Insertar", "Actualizar", "Eliminar"});
                        break;

                }
            }
        });
        operacionComboBox = new JComboBox<>();
        operacionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String operacionSeleccionada = (String) operacionComboBox.getSelectedItem();
                switch (operacionSeleccionada) {
                    case "Buscar":
                        if ("Estudiante".equals(entidadComboBox.getSelectedItem())) {
                            buscarEstudiante();
                        } else if ("Materia".equals(entidadComboBox.getSelectedItem())) {
                            buscarMateria();
                        } else if ("Carrera".equals(entidadComboBox.getSelectedItem())) {
                            buscarCarrera();
                        } else if ("Notas".equals(entidadComboBox.getSelectedItem())) {
                            buscarNotas();
                        } else if ("Estudiante_Tiene_Carrera".equals(entidadComboBox.getSelectedItem())) {
                            buscarEstudianteCarreraPorID();
                        } else if ("Estudiante_Tiene_Materia".equals(entidadComboBox.getSelectedItem())) {
                            buscarEstudianteMateriaPorID();

                        }
                        break;
                    case "Insertar":
                        if ("Estudiante".equals(entidadComboBox.getSelectedItem())) {
                            insertarEstudiante();
                        } else if ("Materia".equals(entidadComboBox.getSelectedItem())) {
                            insertarMateria();
                        } else if ("Carrera".equals(entidadComboBox.getSelectedItem())) {
                            insertarCarrera();
                        } else if ("Notas".equals(entidadComboBox.getSelectedItem())) {
                            insertarNotas();
                        } else if ("Estudiante_Tiene_Carrera".equals(entidadComboBox.getSelectedItem())) {
                            insertarEstudianteCarrera();

                        } else if ("Estudiante_Tiene_Materia".equals(entidadComboBox.getSelectedItem())) {
                            insertarEstudianteMateria();

                        }

                        break;
                    case "Actualizar":
                        if ("Estudiante".equals(entidadComboBox.getSelectedItem())) {
                            actualizarEstudiante();
                        } else if ("Materia".equals(entidadComboBox.getSelectedItem())) {
                            actualizarMateria();
                        } else if ("Carrera".equals(entidadComboBox.getSelectedItem())) {
                            actualizarCarrera();
                        } else if ("Notas".equals(entidadComboBox.getSelectedItem())) {
                            actualizarNotas();
                        } else if ("Estudiante_Tiene_Carrera".equals(entidadComboBox.getSelectedItem())) {
                            actualizarEstudianteCarrera();

                        } else if ("Estudiante_Tiene_Materia".equals(entidadComboBox.getSelectedItem())) {
                            actualizarEstudianteMateria();

                        }
                        break;
                    case "Eliminar":
                        if ("Estudiante".equals(entidadComboBox.getSelectedItem())) {
                            eliminarEstudiante();
                        } else if ("Materia".equals(entidadComboBox.getSelectedItem())) {
                            eliminarMateria();
                        } else if ("Carrera".equals(entidadComboBox.getSelectedItem())) {
                            eliminarCarrera();
                        } else if ("Notas".equals(entidadComboBox.getSelectedItem())) {
                            eliminarNotas();
                        } else if ("Estudiante_Tiene_Carrera".equals(entidadComboBox.getSelectedItem())) {
                            eliminarEstudianteCarrera();
                        } else if ("Estudiante_Tiene_Materia".equals(entidadComboBox.getSelectedItem())) {
                            eliminarEstudianteMateria();

                        }
                        break;
                }

            }
        });
        verTodosComboBox = new JComboBox<>(new String[]{"Estudiantes", "Materias", "Carreras", "Notas", "Estudiante_Tiene_Carrera", "Estudiante_Tiene_Materia"});
        verTodosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTodos();
            }
        });


        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        btnCalcularPromedio = new JButton("Consultar Promedio");


        btnCalcularPromedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPromedioNotaEstudianteMateria();
            }
        });
        btnConsultarMateria = new JButton("Consultar Materia");
        btnConsultarMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarMateria();
            }
        });
        btnMateriaNotas = new JButton("Consultar Notas");
        btnMateriaNotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarNotas();
            }
        });



        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Entidad:"));
        buttonPanel.add(entidadComboBox);
        buttonPanel.add(new JLabel("Operación:"));
        buttonPanel.add(operacionComboBox);
        buttonPanel.add(verTodosComboBox);
        buttonPanel.add(btnSalir);
        buttonPanel.add(btnCalcularPromedio);
        buttonPanel.add(btnConsultarMateria);
        buttonPanel.add(btnMateriaNotas);


        JScrollPane resultadoScrollPane = new JScrollPane(resultadoTextArea);


        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(resultadoScrollPane, BorderLayout.CENTER);

    }





    private void verTodos() {
        String entidadSeleccionada = (String) verTodosComboBox.getSelectedItem();

        switch (entidadSeleccionada) {
            case "Estudiantes":
                verTodosEstudiantes();
                break;
            case "Materias":
                obtenerTodosMaterias();
                break;
            case "Carreras":
                verTodosCarrera();
                break;
            case "Notas":
                verTodosNota();
                break;
            case "Estudiante_Tiene_Carrera":
                verTodosEstudianteCarrera();
                break;
            case "Estudiante_Tiene_Materia":
                verTodosEstudianteMateria();
                break;
        }
    }
    private void mostrarEstudianteEnCuadro(List<EstudianteDTO> estudiantes) {

        resultadoTextArea.setText("");
        for (EstudianteDTO estudiante : estudiantes) {
            resultadoTextArea.append(estudiante.getEstudianteID() + "\t" + estudiante.getNombre() + "\t" + estudiante.getFechanacimiento() + "\n");
        }
    }
    private void mostrarMateriasEnCuadro(List<MateriaDTO> materias) {
        resultadoTextArea.setText("");

        for (MateriaDTO materia : materias) {
            resultadoTextArea.append(materia.getIdMaterial() + "\t" + materia.getNombre() + "\t" + materia.getNumero_creditos() + "\n");


        }
    }
    private void mostrarCarreranCuadro(List<CarreraDTO> carreras) {
        resultadoTextArea.setText("");
        for (CarreraDTO carrera : carreras) {
            resultadoTextArea.append("ID: " + carrera.getIdcarrera() + ", Nombre: " + carrera.getNombre() + "\n");
        }
    }

    private void mostrarNotasCuadro(List<NotasDTO> Notas) {
        resultadoTextArea.setText("");
        for (NotasDTO Nota : Notas) {
            resultadoTextArea.append(Nota.getIdNota() + "\t"   + Nota.getIdEstudiante() + "\t"+ Nota.getIdMateria()+ "\t" + Nota.getPrimerParcial()+ "\t" + Nota.getSegundoParcial() + "\t" + Nota.getFinal_examen() + "\n");

        }
    }
    private void mostrarEstudianteCarreraEnCuadro(List<Estudiante_Tiene_CarreraDTO> estudiantesCarreras) {
        resultadoTextArea.setText("");

        for (Estudiante_Tiene_CarreraDTO estudianteCarrera : estudiantesCarreras) {
            resultadoTextArea.append(estudianteCarrera.getEstudianteID() + "\t" + estudianteCarrera.getIdCarrera() + "\n");
        }
    }
    private void mostrarEstudianteMateriaEnCuadro(List<Estudiante_Tiene_MateriaDTO> estudiantesMaterias) {
        resultadoTextArea.setText("");

        for (Estudiante_Tiene_MateriaDTO estudianteMateria : estudiantesMaterias) {
            resultadoTextArea.append(estudianteMateria.getEstudianteID() + "\t" + estudianteMateria.getIdMateria() + "\n");
        }
    }
    private void insertarEstudiante() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante:");
        String fechaNacimientoStr = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del estudiante (yyyy-MM-dd):");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            Date fechaNacimientoUtilDate = sdf.parse(fechaNacimientoStr);

            java.sql.Date fechaNacimientoSqlDate = new java.sql.Date(fechaNacimientoUtilDate.getTime());

            EstudianteDTO estudiante = new EstudianteDTO();
            estudiante.setNombre(nombre);
            estudiante.setFechanacimiento(fechaNacimientoSqlDate);

            estudianteDAO.registrarEstudiante(estudiante);


            System.out.println("Estudiante registrado exitosamente.");
        } catch (ParseException e) {
            System.out.println("Error: La fecha de nacimiento debe estar en formato yyyy-MM-dd.");
        } catch (Exception e) {
            System.out.println("Error al registrar el estudiante: " + e.getMessage());
        }
    }
    private void actualizarEstudiante() {
        try {
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
            int idEstudianteInt = Integer.parseInt(idEstudiante);

            EstudianteDTO estudianteActual = estudianteDAO.finByIdEstudianteDTO(idEstudianteInt);

            if (estudianteActual == null) {
                JOptionPane.showMessageDialog(this, "No se encontró un estudiante con ese ID.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNacimientoActualStr = sdf.format(estudianteActual.getFechanacimiento());

            resultadoTextArea.setText("Información actual del estudiante:\n");
            resultadoTextArea.append("ID: " + estudianteActual.getEstudianteID() + "\n");
            resultadoTextArea.append("Nombre: " + estudianteActual.getNombre() + "\n");
            resultadoTextArea.append("Fecha de nacimiento: " + fechaNacimientoActualStr + "\n\n");

            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del estudiante:");
            String fechaNacimiento = JOptionPane.showInputDialog("Ingrese la nueva fecha de nacimiento del estudiante (yyyy-MM-dd):");

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            sdf2.setLenient(false);

            java.util.Date nuevaFechaNacimiento = sdf2.parse(fechaNacimiento);
            java.sql.Date sqlDate = new java.sql.Date(nuevaFechaNacimiento.getTime());

            EstudianteDTO estudiante = new EstudianteDTO();
            estudiante.setEstudianteID(idEstudianteInt);
            estudiante.setNombre(nombre);
            estudiante.setFechanacimiento(sqlDate);

            estudianteDAO.modificarEstudiante(estudiante);

            String nuevaFechaNacimientoStr = sdf2.format(nuevaFechaNacimiento);
            resultadoTextArea.append("Información actualizada del estudiante:\n");
            resultadoTextArea.append("ID: " + idEstudianteInt + "\n");
            resultadoTextArea.append("Nombre: " + nombre + "\n");
            resultadoTextArea.append("Fecha de nacimiento: " + nuevaFechaNacimientoStr + "\n\n");

        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error: La fecha de nacimiento debe estar en formato yyyy-MM-dd.");
            resultadoTextArea.setText("Error: La fecha de nacimiento debe estar en formato yyyy-MM-dd.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el estudiante: " + e.getMessage());
            resultadoTextArea.setText("Error al actualizar el estudiante");
        }
    }




    private void eliminarEstudiante() {
        try {
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
            int estudianteID = Integer.parseInt(idEstudiante);

            EstudianteDTO estudiante = new EstudianteDTO();
            estudiante.setEstudianteID(estudianteID);

            estudianteDAO.eliminarEstudiante(estudiante);

            resultadoTextArea.setText("Estudiante eliminado exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al eliminar el estudiante");
        }
    }
    private void buscarEstudiante() {
        String idEstudianteStr = JOptionPane.showInputDialog("Ingrese el ID del estudiante que desea buscar:");

        if (idEstudianteStr == null || idEstudianteStr.isEmpty()) {
            return;
        }

        int idEstudiante;

        try {
            idEstudiante = Integer.parseInt(idEstudianteStr);
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
            return;
        }

        try {
            EstudianteDTO estudianteEncontrado = estudianteDAO.finByIdEstudianteDTO(idEstudiante);

            if (estudianteEncontrado != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaNacimientoStr = sdf.format(estudianteEncontrado.getFechanacimiento());

                resultadoTextArea.append("Información del estudiante encontrado:\n");
                resultadoTextArea.append("ID: " + estudianteEncontrado.getEstudianteID() + "\n");
                resultadoTextArea.append("Nombre: " + estudianteEncontrado.getNombre() + "\n");
                resultadoTextArea.append("Fecha de nacimiento: " + fechaNacimientoStr + "\n\n");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un estudiante con ese ID.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al procesar la información.");
        }
    }


    private void verTodosEstudiantes() {
        try {
            EstudianteDAO estudianteDAO = new EstudianteDAO();
            List<EstudianteDTO> todosEstudiantes = estudianteDAO.obtenerTodosEstudiantes();
            mostrarEstudianteEnCuadro(todosEstudiantes);
        } catch (SQLException e) {
            System.out.println("Error SQL al ver todos los estudiantes: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al ver todos los estudiantes: " + e.getMessage());
        }
    }



    private void insertarMateria() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la materia:");
        String numero_creditos = JOptionPane.showInputDialog("Ingrese el numero de creditos de la materia:");
        try {
            MateriaDTO materia = new MateriaDTO();
            materia.setNombre(nombre);
            materia.setNumero_creditos(Integer.parseInt(numero_creditos));
            materiaDAO.registrarMateria(materia);
            resultadoTextArea.setText("Materia registrada exitosamente");
        } catch (Exception e) {
            System.out.println("Error al registrar la materia");
        }
    }
    private void actualizarMateria() {
        try {
            String idMateria = JOptionPane.showInputDialog("Ingrese el ID de la materia:");
            int idMaterial = Integer.parseInt(idMateria);

            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la materia:");
            String numero_creditos = JOptionPane.showInputDialog("Ingrese el nuevo numero de creditos de la materia:");

            MateriaDTO materia = new MateriaDTO();
            materia.setIdMaterial(idMaterial);
            materia.setNombre(nombre);
            materia.setNumero_creditos(Integer.parseInt(numero_creditos));

            materiaDAO.modificarMateria(materia);

            resultadoTextArea.setText("Materia actualizada exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al actualizar la materia");
        }
    }
    private void eliminarMateria() {
        try {
            String idMateria = JOptionPane.showInputDialog("Ingrese el ID de la materia:");
            int idMaterial = Integer.parseInt(idMateria);

            MateriaDTO materia = new MateriaDTO();
            materia.setIdMaterial(idMaterial);

            materiaDAO.eliminarMateria(materia);

            resultadoTextArea.setText("Materia eliminada exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al eliminar la materia");
        }
    }
    private void buscarMateria() {
        String idMateriaStr = JOptionPane.showInputDialog("Ingrese el ID de la materia que desea buscar:");

        if (idMateriaStr == null || idMateriaStr.isEmpty()) {
            return;
        }

        int idMateria;

        try {
            idMateria = Integer.parseInt(idMateriaStr);
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
            return;
        }

        try {
            MateriaDTO materiaEncontrada = materiaDAO.findByIdMateriaDTO(idMateria);

            if (materiaEncontrada != null) {
                resultadoTextArea.append("Información de la materia encontrada:\n");
                resultadoTextArea.append("ID: " + materiaEncontrada.getIdMaterial() + "\n");
                resultadoTextArea.append("Nombre: " + materiaEncontrada.getNombre() + "\n");
                resultadoTextArea.append("Número de créditos: " + materiaEncontrada.getNumero_creditos() + "\n\n");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una materia con ese ID.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar la materia: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al procesar la información.");
        }
    }

    private void obtenerTodosMaterias() {
        try {
            MateriaDAO dao = new MateriaDAO();
            List<MateriaDTO> todasMaterias = dao.obtenerTodosMaterias();
            if (todasMaterias.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay Materias con carreras registradas.");
            } else {
                mostrarMateriasEnCuadro(todasMaterias);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener las Materias con carreras desde la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error SQL al ver todos los Materias con carreras: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al ver todas las Materias con carreras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al ver todas las Materias con carreras: " + e.getMessage());
        }
    }
    private void consultarMateria() {
        try {
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
            int estudianteId = Integer.parseInt(idEstudiante);

            List<MateriaDTO> materias = materiaDAO.obtenerMateriasDeEstudiante(estudianteId);

            if (materias.isEmpty()) {
                resultadoTextArea.setText("El estudiante no tiene materias registradas.");
            } else {
                resultadoTextArea.setText("Materias del estudiante:\n");
                for (MateriaDTO materia : materias) {
                    resultadoTextArea.append("ID: " + materia.getIdMaterial() + "\n");
                    resultadoTextArea.append("Nombre: " + materia.getNombre() + "\n");
                    resultadoTextArea.append("Número de créditos: " + materia.getNumero_creditos() + "\n\n");
                }
            }
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido para el estudiante.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al obtener las materias del estudiante.");
        }
    }







    private void insertarCarrera() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la carrera:");
        try {
            CarreraDTO carrera = new CarreraDTO();
            carrera.setNombre(nombre);
            carreraDAO.registrarCarrera(carrera);
            resultadoTextArea.setText("Carrera registrada exitosamente");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al registrar la carrera");
        }
    }
    private void actualizarCarrera() {
        try {
            String idCarrera = JOptionPane.showInputDialog("Ingrese el ID de la carrera:");
            int idcarrera = Integer.parseInt(idCarrera);

            String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la carrera:");

            CarreraDTO carrera = new CarreraDTO();
            carrera.setIdcarrera(idcarrera);
            carrera.setNombre(nombre);

            carreraDAO.modificarCarrera(carrera);

            resultadoTextArea.setText("Carrera actualizada exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al actualizar la carrera");
        }
    }
    private void eliminarCarrera() {
        try {
            String idCarrera = JOptionPane.showInputDialog("Ingrese el ID de la carrera:");
            int idcarrera = Integer.parseInt(idCarrera);

            CarreraDTO carrera = new CarreraDTO();
            carrera.setIdcarrera(idcarrera);

            carreraDAO.eliminarCarrera(carrera);

            resultadoTextArea.setText("Carrera eliminada exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al eliminar la carrera");
        }
    }
    private void buscarCarrera() {
        String CarreraIDStr = JOptionPane.showInputDialog("Ingrese el ID de la carrera que desea buscar:");

        if (CarreraIDStr == null || CarreraIDStr.isEmpty()) {
            return;
        }

        int idCarrera;

        try {
            idCarrera = Integer.parseInt(CarreraIDStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.");
            return;
        }

        try {
            CarreraDAO carreraDAO = new CarreraDAO();
            CarreraDTO carreraEncontrada = carreraDAO.findByIdCarreraDTO(idCarrera);

            if (carreraEncontrada != null) {
                resultadoTextArea.setText("Información de la carrera encontrada:\n");
                resultadoTextArea.append("ID: " + carreraEncontrada.getIdcarrera() + "\n");
                resultadoTextArea.append("Nombre: " + carreraEncontrada.getNombre() + "\n");

            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una Carrera con ese ID.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al procesar la información.");
        }
    }

    private void verTodosCarrera() {
        try {
            CarreraDAO carreraDAO = new CarreraDAO();
            List<CarreraDTO> todasCarreras = carreraDAO.obtenerTodosCarreras();
            if (todasCarreras.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay carreras registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarCarreranCuadro(todasCarreras);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener las carreras desde la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error SQL al ver todas las carreras: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al ver todas las carreras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al ver todas las carreras: " + e.getMessage());
        }
    }



    private void insertarNotas() {
        try {

            String calificacion = JOptionPane.showInputDialog("Ingrese la nota del primer Parcial:");
            String calificacion2 = JOptionPane.showInputDialog("Ingrese la nota del segundo Parcial:");
            String calificacion3 = JOptionPane.showInputDialog("Ingrese la nota del final:");
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el id del estudiante:");
            String idMateria = JOptionPane.showInputDialog("Ingrese el id de la materia:");

            NotasDTO notas = new NotasDTO();

            notas.setPrimerParcial(Double.parseDouble(calificacion));
            notas.setSegundoParcial(Double.parseDouble(calificacion2));
            notas.setFinal_examen(Double.parseDouble(calificacion3));
            notas.setIdEstudiante(Integer.parseInt(idEstudiante));
            notas.setIdMateria(Integer.parseInt(idMateria));

            notasDAO.registrarNota(notas);

            resultadoTextArea.setText("Nota registrada exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Error: Los valores ingresados no son números válidos.");
            e.printStackTrace();
        } catch (Exception e) {
            resultadoTextArea.setText("Error al registrar la nota");
            e.printStackTrace();
        }
    }

    private void actualizarNotas() {
        try {
            String idNota = JOptionPane.showInputDialog("Ingrese el ID de la nota:");
            int idNotaInt = Integer.parseInt(idNota);

            String calificacion = JOptionPane.showInputDialog("Ingrese la nota del primer parcial:");
            String calificacion2 = JOptionPane.showInputDialog("Ingrese la nota del segundo parcial:");
            String calificacion3 = JOptionPane.showInputDialog("Ingrese la nota del final:");
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el nuevo id del estudiante:");
            String idMateria = JOptionPane.showInputDialog("Ingrese el nuevo id de la materia:");

            NotasDTO notas = new NotasDTO();
            notas.setIdNota(idNotaInt);
            notas.setPrimerParcial(Double.parseDouble(calificacion));
            notas.setSegundoParcial(Double.parseDouble(calificacion2));
            notas.setFinal_examen(Double.parseDouble(calificacion3));
            notas.setIdEstudiante(Integer.parseInt(idEstudiante));
            notas.setIdMateria(Integer.parseInt(idMateria));

            notasDAO.modificarNotas(notas);

            resultadoTextArea.setText("Nota actualizada exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al actualizar la nota");
        }
    }
    private void eliminarNotas() {
        try {
            String idNota = JOptionPane.showInputDialog("Ingrese el ID de la nota:");
            int idNotaInt = Integer.parseInt(idNota);

            NotasDTO notas = new NotasDTO();
            notas.setIdNota(idNotaInt);

            notasDAO.eliminarNotas(notas);

            resultadoTextArea.setText("Nota eliminada exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al eliminar la nota");
        }
    }
    private void buscarNotas() {
        try {
            String idNotaStr = JOptionPane.showInputDialog("Ingrese el ID de la nota que desea buscar:");

            if (idNotaStr == null || idNotaStr.isEmpty()) {
                return;
            }

            int idNota = Integer.parseInt(idNotaStr);

            NotasDTO notasEncontrada = notasDAO.findByIdNotasDTO(idNota);

            if (notasEncontrada != null) {
                resultadoTextArea.append("Información de la nota encontrada:\n");
                resultadoTextArea.append("ID: " + notasEncontrada.getIdNota() + "\n");
                resultadoTextArea.append("Primer_Parcial: " + notasEncontrada.getPrimerParcial() + "\n");
                resultadoTextArea.append("Segundo_Parcial: " + notasEncontrada.getSegundoParcial() + "\n");
                resultadoTextArea.append("Final_Examen: " + notasEncontrada.getFinal_examen() + "\n");
                resultadoTextArea.append("ID del estudiante: " + notasEncontrada.getIdEstudiante() + "\n");
                resultadoTextArea.append("ID de la materia: " + notasEncontrada.getIdMateria() + "\n\n");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una nota con ese ID.");
            }
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            System.out.println("Error al buscar la nota"+  e.getMessage());
        }
    }
    private void verTodosNota() {
        try {
            NotasDAO notasDAO = new NotasDAO();
            List<NotasDTO> todasNotas = notasDAO.obtenerTodosNotas();
            if (todasNotas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay notas registradas.");
            } else {
                mostrarNotasCuadro(todasNotas);
            }

        } catch (Exception e) {
            System.out.println("Error al ver todas las notas: " + e.getMessage());
        }
    }
    private void calcularPromedioNotaEstudianteMateria() {
        try {
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
            String idMateria = JOptionPane.showInputDialog("Ingrese el ID de la materia:");

            int estudianteId = Integer.parseInt(idEstudiante);
            int materiaId = Integer.parseInt(idMateria);

            BigDecimal promedioNota = notasDAO.calcularPromedioNota(estudianteId, materiaId);

            resultadoTextArea.setText("El promedio de notas del estudiante en la materia es: " + promedioNota);
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al calcular el promedio de notas.");
        }
    }
    private void consultarNotas() {
        try {
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
            String idMateria = JOptionPane.showInputDialog("Ingrese el ID de la materia:");

            int estudianteId = Integer.parseInt(idEstudiante);
            int materiaId = Integer.parseInt(idMateria);

            List<NotasDTO> notas = notasDAO.verNotasEstudianteMateria(estudianteId, materiaId);

            if (!notas.isEmpty()) {
                resultadoTextArea.append("Notas del estudiante en la materia:\n");
                for (NotasDTO nota : notas) {
                    resultadoTextArea.append("Primer Parcial: " + nota.getPrimerParcial() + "\n");
                    resultadoTextArea.append("Segundo Parcial: " + nota.getSegundoParcial() + "\n");
                    resultadoTextArea.append("Final Examen: " + nota.getFinal_examen() + "\n\n");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron notas para el estudiante en la materia especificada.");
            }
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al obtener las notas del estudiante en la materia: " + e.getMessage());
        }
    }




    private void buscarEstudianteMateriaPorID() {
        try {
            String idEstudianteMateria = JOptionPane.showInputDialog("Ingrese el ID del estudiante_materia que desea buscar:");

            if (idEstudianteMateria == null || idEstudianteMateria.isEmpty()) {
                return;
            }

            int idEstudianteMateriaInt = Integer.parseInt(idEstudianteMateria);

            Estudiante_Tiene_MateriaDAO dao = new Estudiante_Tiene_MateriaDAO();
            Estudiante_Tiene_MateriaDTO estudianteMateriaEncontrado = dao.findByIdEstudiante_Tiene_MateriaDTO(idEstudianteMateriaInt);

            if (estudianteMateriaEncontrado != null) {
                resultadoTextArea.append("Información del estudiante_materia encontrado:\n");
                resultadoTextArea.append("ID del estudiante: " + estudianteMateriaEncontrado.getEstudianteID() + "\n");
                resultadoTextArea.append("ID de la materia: " + estudianteMateriaEncontrado.getIdMateria() + "\n\n");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un estudiante_materia con ese ID.");
            }
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al buscar el estudiante_materia");
        }
    }
    private void insertarEstudianteMateria() {
        try {
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
            String idMateria = JOptionPane.showInputDialog("Ingrese el ID de la materia:");

            Estudiante_Tiene_MateriaDTO estudianteMateria = new Estudiante_Tiene_MateriaDTO();
            estudianteMateria.setEstudianteID(Integer.parseInt(idEstudiante));
            estudianteMateria.setIdMateria(Integer.parseInt(idMateria));

            Estudiante_Tiene_MateriaDAO dao = new Estudiante_Tiene_MateriaDAO();
            dao.registrarEstudiante_Tiene_Materia(estudianteMateria);

            resultadoTextArea.setText("Estudiante_materia registrado exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Error: Los valores ingresados no son números válidos.");
            e.printStackTrace();
        } catch (Exception e) {
            resultadoTextArea.setText("Error al registrar el estudiante_materia");
            e.printStackTrace();
        }
    }
    private void actualizarEstudianteMateria() {
        try {
            String idEstudianteMateria = JOptionPane.showInputDialog("Ingrese el ID del estudiante_materia:");
            int idEstudianteMateriaInt = Integer.parseInt(idEstudianteMateria);

            String idEstudiante = JOptionPane.showInputDialog("Ingrese el nuevo ID del estudiante:");
            String idMateria = JOptionPane.showInputDialog("Ingrese el nuevo ID de la materia:");

            Estudiante_Tiene_MateriaDTO estudianteMateria = new Estudiante_Tiene_MateriaDTO();
            estudianteMateria.setEstudianteID(Integer.parseInt(idEstudiante));
            estudianteMateria.setIdMateria(Integer.parseInt(idMateria));
            estudianteMateria.setEstudianteID(idEstudianteMateriaInt);

            Estudiante_Tiene_MateriaDAO dao = new Estudiante_Tiene_MateriaDAO();
            dao.modificarEstudiante_Tiene_Materia(estudianteMateria);

            resultadoTextArea.setText("Estudiante_materia actualizado exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al actualizar el estudiante_materia");
        }
    }
    private void eliminarEstudianteMateria() {
        try {
            String idEstudianteMateria = JOptionPane.showInputDialog("Ingrese el ID del estudiante_materia:");
            int idEstudianteMateriaInt = Integer.parseInt(idEstudianteMateria);

            Estudiante_Tiene_MateriaDTO estudianteMateria = new Estudiante_Tiene_MateriaDTO();
            estudianteMateria.setEstudianteID(idEstudianteMateriaInt);

            Estudiante_Tiene_MateriaDAO dao = new Estudiante_Tiene_MateriaDAO();
            dao.eliminarEstudiante_Tiene_Materia(estudianteMateria);

            resultadoTextArea.setText("Estudiante_materia eliminado exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al eliminar el estudiante_materia");
        }
    }
    private void verTodosEstudianteMateria() {
        try {
            Estudiante_Tiene_MateriaDAO dao = new Estudiante_Tiene_MateriaDAO();
            List<Estudiante_Tiene_MateriaDTO> todasEstudiantesTieneMateria = dao.obtenerTodosEstudiantesTieneMateria();
            if (todasEstudiantesTieneMateria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay estudiantes con materias registradas.");
            } else {
                mostrarEstudianteMateriaEnCuadro(todasEstudiantesTieneMateria);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los estudiantes con materias desde la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error SQL al ver todos los estudiantes con materias: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al ver todos los estudiantes con materias: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al ver todos los estudiantes con materias: " + e.getMessage());
        }
    }


    private void buscarEstudianteCarreraPorID() {
        try {
            String idEstudianteCarrera = JOptionPane.showInputDialog("Ingrese el ID del estudiante_carrera que desea buscar:");

            if (idEstudianteCarrera == null || idEstudianteCarrera.isEmpty()) {
                return;
            }

            int idEstudianteCarreraInt = Integer.parseInt(idEstudianteCarrera);

            Estudiante_Tiene_CarreraDAO dao = new Estudiante_Tiene_CarreraDAO(); // Crear una instancia de Estudiante_Tiene_CarreraDAO
            Estudiante_Tiene_CarreraDTO estudianteCarreraEncontrado = dao.findByIdEstudiante_Tiene_CarreraDTO(idEstudianteCarreraInt);

            if (estudianteCarreraEncontrado != null) {
                resultadoTextArea.append("Información del estudiante_carrera encontrado:\n");
                resultadoTextArea.append("ID del estudiante: " + estudianteCarreraEncontrado.getEstudianteID() + "\n");
                resultadoTextArea.append("ID de la carrera: " + estudianteCarreraEncontrado.getIdCarrera() + "\n\n");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un estudiante_carrera con ese ID.");
            }
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al buscar el estudiante_carrera");
        }
    }
    private void insertarEstudianteCarrera() {
        try {
            String idEstudiante = JOptionPane.showInputDialog("Ingrese el ID del estudiante:");
            String idCarrera = JOptionPane.showInputDialog("Ingrese el ID de la carrera:");

            Estudiante_Tiene_CarreraDTO estudianteCarrera = new Estudiante_Tiene_CarreraDTO();
            estudianteCarrera.setEstudianteID(Integer.parseInt(idEstudiante));
            estudianteCarrera.setIdCarrera(Integer.parseInt(idCarrera));

            Estudiante_Tiene_CarreraDAO dao = new Estudiante_Tiene_CarreraDAO();
            dao.registrarEstudiante_Tiene_Carrera(estudianteCarrera);

            resultadoTextArea.setText("Estudiante_carrera registrado exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Error: Los valores ingresados no son números válidos.");
            e.printStackTrace();
        } catch (Exception e) {
            resultadoTextArea.setText("Error al registrar el estudiante_carrera");
            e.printStackTrace();
        }
    }
    private void actualizarEstudianteCarrera() {
        try {
            String idEstudianteCarrera = JOptionPane.showInputDialog("Ingrese el ID del estudiante_carrera:");
            int idEstudianteCarreraInt = Integer.parseInt(idEstudianteCarrera);

            String idEstudiante = JOptionPane.showInputDialog("Ingrese el nuevo ID del estudiante:");
            String idCarrera = JOptionPane.showInputDialog("Ingrese el nuevo ID de la carrera:");

            Estudiante_Tiene_CarreraDTO estudianteCarrera = new Estudiante_Tiene_CarreraDTO();
            estudianteCarrera.setEstudianteID(Integer.parseInt(idEstudiante));
            estudianteCarrera.setIdCarrera(Integer.parseInt(idCarrera));
            estudianteCarrera.setEstudianteID(idEstudianteCarreraInt);

            Estudiante_Tiene_CarreraDAO dao = new Estudiante_Tiene_CarreraDAO();
            dao.modificarNota_Tiene_Carrera(estudianteCarrera);

            resultadoTextArea.setText("Estudiante_carrera actualizado exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al actualizar el estudiante_carrera");
        }
    }
    private void eliminarEstudianteCarrera() {
        try {
            String idEstudianteCarrera = JOptionPane.showInputDialog("Ingrese el ID del estudiante_carrera:");
            int idEstudianteCarreraInt = Integer.parseInt(idEstudianteCarrera);

            Estudiante_Tiene_CarreraDTO estudianteCarrera = new Estudiante_Tiene_CarreraDTO();
            estudianteCarrera.setEstudianteID(idEstudianteCarreraInt);

            Estudiante_Tiene_CarreraDAO dao = new Estudiante_Tiene_CarreraDAO();
            dao.eliminarNota_Tiene_Carrera(estudianteCarrera);

            resultadoTextArea.setText("Estudiante_carrera eliminado exitosamente");
        } catch (NumberFormatException e) {
            resultadoTextArea.setText("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            resultadoTextArea.setText("Error al eliminar el estudiante_carrera");
        }
    }

    private void verTodosEstudianteCarrera() {
        try {
            Estudiante_Tiene_CarreraDAO dao = new Estudiante_Tiene_CarreraDAO();
            List<Estudiante_Tiene_CarreraDTO> todasEstudiantesTieneCarrera = dao.obtenerTodosEstudiantesTieneCarrera();
            if (todasEstudiantesTieneCarrera.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay estudiantes con carreras registradas.");
            } else {
                mostrarEstudianteCarreraEnCuadro(todasEstudiantesTieneCarrera);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los estudiantes con carreras desde la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error SQL al ver todos los estudiantes con carreras: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al ver todos los estudiantes con carreras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al ver todos los estudiantes con carreras: " + e.getMessage());
        }
    }









    private void cargarOperaciones (String[]operaciones){
            operacionComboBox.removeAllItems();
            for (String operacion : operaciones) {
                operacionComboBox.addItem(operacion);
            }
        }





    private void salir() {
        System.out.println("--------Gracias por usar la base de datos:)----------- ");
        System.exit(0);
    }
    public static void main (String[]args){
        SwingUtilities.invokeLater(() -> new InterfazGrafica().setVisible(true));
    }
}
