package com.bajicdusko.kotlinstarterkit.ui.fragment.manager

import org.parceler.Parcel


/**
 * Created by Dusko Bajic on 07.06.17.
 * GitHub @bajicdusko
 */
@Parcel(Parcel.Serialization.BEAN)
class FragmentTagStack constructor(var tags: MutableList<String> = ArrayList<String>(), var activeTag: String? = null) {

    @Transient private var showLogs: Boolean = false

    /**
     * Enabling or disabling fragment stack logs. Logs are disabled by default.

     * @param showLogs
     */
    fun setShowLogs(showLogs: Boolean) {
        this.showLogs = showLogs
    }

    /**
     * Pushing new tag to stack and setting is as [FragmentTagStack.activeTag]

     * @param tag
     */
    fun push(tag: String) {
        tags.add(tag)
        activeTag = tag
        logStack()
    }

    /**
     * Popping tag from the stack and setting the tag below as new [FragmentTagStack.activeTag]
     *
     *
     * Example:
     *
     *
     * Stack [3, 2, 1, 0] (3 is last added tag). [FragmentTagStack.activeTag] have value 3
     *
     *
     * Now we're popping the last added tag
     *
     *
     * Stack [2, 1, 0] [FragmentTagStack.activeTag] have value 2

     * @return Popped up value. From the example above, returned value will be 3. In case of empty stack, null will be returned.
     */
    fun pop(): String? {
        val tag = peek()
        if (!android.text.TextUtils.isEmpty(tag)) {
            tags.removeAt(tags.size - 1)
        }

        activeTag = peek()
        logStack()
        return tag
    }

    /**
     * Peeking to the top of the stack, without data modification.

     * @return Value on top of the stack. If stack is empty, returned value will be null.
     */
    fun peek(): String? {
        if (tags.size > 0) {
            return tags[tags.size - 1]
        } else {
            return null
        }
    }

    private fun logStack() {
        if (showLogs) {
            val stringBuilder = StringBuilder()
            stringBuilder.append("Active tag: $activeTag\nStack:\n")
            for (i in tags.size - 1 downTo 0) {
                stringBuilder.append("[ ${tags[i]}]\n")
            }
            android.util.Log.d(com.bajicdusko.kotlinstarterkit.ui.fragment.manager.FragmentTagStack.Companion.TAG, stringBuilder.toString())
        }
    }

    /**
     * Clears the stack.
     */
    fun popUpAll() {
        tags.clear()
    }

    companion object {

        @Transient private val TAG = "FragmentTagStack"
    }
}