package Modelo.Mapper;

import Modelo.Cultivo;
import Modelo.Dao.CultivoDAO;
import Modelo.Dto.CultivoDTO;
import java.util.List;

public class CultivoService {

    private CultivoDAO cultivoDAO = new CultivoDAO();

    public List<CultivoDTO> obtenerTodosLosCultivos() {
        List<Cultivo> entidades = cultivoDAO.seleccionarTodos();
        return CultivoMapper.toDTOList(entidades);
    }

    public CultivoDTO obtenerCultivoPorId(int id) {
        Cultivo entidad = cultivoDAO.seleccionarPorId(id);
        return CultivoMapper.toDTO(entidad);
    }

    public boolean guardarCultivo(CultivoDTO cultivoDTO) {
        Cultivo nuevoCultivo = CultivoMapper.toEntidad(cultivoDTO);
        return cultivoDAO.insertar(nuevoCultivo);
    }

    public boolean actualizarCultivo(CultivoDTO cultivoDTO) {
        Cultivo cultivoActualizado = CultivoMapper.toEntidad(cultivoDTO);
        return cultivoDAO.actualizar(cultivoActualizado);
    }

    public boolean eliminarCultivo(int id) {
        return cultivoDAO.eliminar(id);
    }
}