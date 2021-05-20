package com.vahitkeskin.kotlinanomalydetectioninbinarytree

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.vahitkeskin.kotlinanomalydetectioninbinarytree.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun resultScanner(view: View) {

        val inputOne = binding.etInputOne.text.toString()
        val inputTwo = binding.etInputTwo.text.toString()
        val inputThree = binding.etInputThree.text.toString()
        val inputFour = binding.etInputFour.text.toString()

        when {
            inputOne.isEmpty() -> {
                binding.etInputOne.error = "Input1 null!"
            }
            inputTwo.isEmpty() -> {
                binding.etInputTwo.error = "Input2 null!"
            }
            inputThree.isEmpty() -> {
                binding.etInputThree.error = "Input3 null!"
            }
            inputFour.isEmpty() -> {
                binding.etInputFour.error = "Input4 null!"
            }
            else -> {
                binding.llBranch.isVisible = true
                binding.btnClear.isVisible = true
                val root = Node(inputOne.toInt())
                root.left = Node(inputTwo.toInt())
                root.right = Node(inputThree.toInt())
                root.left!!.left = Node(inputFour.toInt())
                binding.tvResult.text = "Result \"${resultBoolean(root)}\""

                binding.tvBranchOn.text = inputOne
                binding.tvBranchTwo.text = inputTwo
                binding.tvBranchThree.text = inputThree
                binding.tvBranchFour.text = inputFour

            }
        }
    }

    fun clearScanner(view: View) {
        binding.etInputOne.text.clear()
        binding.etInputTwo.text.clear()
        binding.etInputThree.text.clear()
        binding.etInputFour.text.clear()

        binding.llBranch.isVisible = false
        binding.btnClear.isVisible = false
    }

    private fun resultBoolean(node: Node): Boolean {
        return checkDup(node)
    }

    private fun checkDupUtil(root: Node?, hashSet: HashSet<Int?>): Boolean {

        if (root == null) return false

        if (hashSet.contains(root.data)) return true

        hashSet.add(root.data)

        return checkDupUtil(root.left, hashSet) || checkDupUtil(root.right, hashSet)
    }

    private fun checkDup(root: Node?): Boolean {
        val hashSet: HashSet<Int?> = HashSet()
        return checkDupUtil(root, hashSet)
    }
}