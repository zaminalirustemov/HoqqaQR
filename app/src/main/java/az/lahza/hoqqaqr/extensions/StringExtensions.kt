package az.lahza.hoqqaqr.extensions


/**
 * Returns an empty [String].
 *
 * This function provides a more readable and meaningful way to represent an empty string
 * (`""`). It can be used wherever an empty string is needed, improving code readability
 * and reducing the use of hardcoded values in your project.
 *
 * Example usage:
 * ```
 * val emptyString = String.empty()
 * binding.textView.text = String.empty()
 * ```
 *
 * @return An empty string.
 */
fun String.Companion.empty() = ""