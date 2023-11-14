//package com.example.midtermproject
//
//import android.app.AlertDialog
//import android.app.Dialog
//import android.os.Bundle
//import androidx.fragment.app.DialogFragment
//
//class DeleteConfirmationDialogFragment (private val score: Score, private val onConfirmDelete: (Score) -> Unit) :
//    DialogFragment() {
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return AlertDialog.Builder(requireContext())
//            .setMessage("Are you sure you want to delete this score?")
//            .setPositiveButton("Delete") { _, _ ->
//                onConfirmDelete(score)
//            }
//            .setNegativeButton("Cancel") { dialog, _ ->
//                dialog.cancel()
//            }
//            .create()
//    }
//}