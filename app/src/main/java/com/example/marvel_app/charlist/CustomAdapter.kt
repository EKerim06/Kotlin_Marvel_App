import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel_app.R
import com.example.marvel_app.models.Character
import com.example.marvel_app.models.CharacterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class CustomAdapter(
    private val context: Context,
    private var characters: List<Character>,
    private val onItemClick: (Character) -> Unit,
) : PagingDataAdapter<CharacterResponse, CustomAdapter.ViewHolder>(differCallback) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val charName: TextView = itemView.findViewById(R.id.textView)
        val charImage: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(characters[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.characters_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.setIsRecyclable(false)
        holder.charName.text = character.name

        Glide.with(context)
            .load(character.thumbnail.path + "." + character.thumbnail.extension)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(holder.charImage)


    }


    override fun getItemCount(): Int {
        return characters.size
    }

    fun setData(newCharacters: List<Character>) {
        characters = newCharacters
        notifyDataSetChanged()
    }


    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<CharacterResponse>() {
            override fun areItemsTheSame(
                oldItem: CharacterResponse,
                newItem: CharacterResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CharacterResponse,
                newItem: CharacterResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


}
