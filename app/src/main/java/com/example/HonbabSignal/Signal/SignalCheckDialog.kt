import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.HonbabSignal.Signal.CustomDialog2
import com.example.HonbabSignal.databinding.DialogSignalCheckBinding

class CustomDialog : DialogFragment() {
    private var _binding: DialogSignalCheckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogSignalCheckBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.signalCheckPassTv.setOnClickListener {
            dismiss()    // 대화상자를 닫는 함수
        }
        binding.signalCheckCompleteTv.setOnClickListener {
            dismiss()
            val dialog = CustomDialog2()
            dialog.show(parentFragmentManager, "CustomDialog2")
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}