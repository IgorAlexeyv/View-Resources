package otus.gpb.homework.viewandresources.components

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import otus.gpb.homework.viewandresources.R
import otus.gpb.homework.viewandresources.databinding.CompoundViewContactsBinding


class CompoundViewContacts @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStileAttr: Int = 0
) : ConstraintLayout(context, attrs, defStileAttr) {

    private val binding: CompoundViewContactsBinding

    init {
        binding = CompoundViewContactsBinding.inflate(LayoutInflater.from(context), this)
        initCustomAttibutes(attrs, defStileAttr)
    }

    private fun initCustomAttibutes(attrs: AttributeSet?, defStyleAttr: Int) = with(binding) {
        context.withStyledAttributes(attrs, R.styleable.CompoundViewContacts, defStyleAttr, 0) {
            title.text = getString(R.styleable.CompoundViewContacts_contacts_title)
            title.setTextColor(getColor(R.styleable.CompoundViewContacts_contacts_titleColor, 0))
            text.text = getString(R.styleable.CompoundViewContacts_contacts_text)
            text.setTextColor(getColor(R.styleable.CompoundViewContacts_contacts_textColor, 0))
            binding.icon.setImageDrawable(getDrawable(R.styleable.CompoundViewContacts_contacts_icon))
            binding.icon.setImageTintList(
                ColorStateList.valueOf(
                    getColor(R.styleable.CompoundViewContacts_contacts_iconColor, 0)))
            binding.divider.dividerColor = (getColor(R.styleable.CompoundViewContacts_contacts_dividerColor, 0))
        }
    }
}