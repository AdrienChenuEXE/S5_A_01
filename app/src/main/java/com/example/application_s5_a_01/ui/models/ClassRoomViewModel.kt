package com.example.application_s5_a_01.ui.models
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
import com.example.application_s5_a_01.data.enums.Measures
import com.example.application_s5_a_01.model.MeasureSettings
import com.example.application_s5_a_01.model.MeasuresData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MeasureUiState {
    data class Success(
        val measures: MeasuresData,
    ) : MeasureUiState
    object Error : MeasureUiState
    object Loading : MeasureUiState
}

class ClassRoomViewModel(
    private val measureRepository: MeasureRepository,
    settings: MeasureSettings // Pass settings in the constructor
) : ViewModel() {
    var settings by mutableStateOf(settings)
        private set

    var measureUiView: MeasureUiState by mutableStateOf(MeasureUiState.Loading)
        private set

    init {
        getMeasures()
    }

    fun setMeasure(measure: Measures) {
        this.settings.measure = measure
    }

    fun getMeasures() {
        viewModelScope.launch {
            measureUiView = MeasureUiState.Loading
            measureUiView = try {
                val measureQuery = settings.toMeasureQuery()
                val measures = measureRepository.getMeasures(measureQuery)
                MeasureUiState.Success(measures)
            } catch (e: IOException) {
                println(e.message)
                MeasureUiState.Error
            } catch (e: HttpException) {
                println(e.message())
                MeasureUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SAEApplication)
                val measureRepository = application.container.appRepository
                val settings = MeasureSettings()
                ClassRoomViewModel(
                    measureRepository = measureRepository,
                    settings = settings
                )
            }
        }
    }
}