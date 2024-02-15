
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.application_s5_a_01.SAEApplication
import com.example.application_s5_a_01.data.MeasureRepository
import com.example.application_s5_a_01.model.MeasureQuery
import com.example.application_s5_a_01.model.Salle
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MeasureUiState {
    data class Success(val measures: List<Salle>) : MeasureUiState
    object Error : MeasureUiState
    object Loading : MeasureUiState
}

class ClassRoomViewModel(private val measureRepository: MeasureRepository) : ViewModel() {
    var measureUiView: MeasureUiState by mutableStateOf(MeasureUiState.Loading)
        private set

    fun getMeasures(
        measureQuery: MeasureQuery
    ) {
        viewModelScope.launch {
            measureUiView = MeasureUiState.Loading
            measureUiView = try {
                MeasureUiState.Success(measureRepository.getMeasures(measureQuery))
            } catch (e: IOException) {
                MeasureUiState.Error
            } catch (e: HttpException) {
                MeasureUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SAEApplication)
                val measureRepository = application.container.appRepository
                ClassRoomViewModel(measureRepository = measureRepository)
            }
        }
    }
}