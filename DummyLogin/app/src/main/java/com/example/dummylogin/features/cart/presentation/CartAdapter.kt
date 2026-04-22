import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dummylogin.databinding.ItemCartBinding
import com.example.dummylogin.features.cart.CartItem
import com.example.dummylogin.features.cart.CartManager

class CartAdapter(
    private val items: List<CartItem>,
    private val onClick: (CartItem) -> Unit,
    private val onRemove: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(
        val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        holder.binding.tvTitle.text = item.title
        holder.binding.tvPrice.text = "R$ ${item.price}"
        holder.binding.tvQuantity.text = "x${item.quantity}"

        Glide.with(holder.binding.imgProduct)
            .load(item.thumbnail)
            .into(holder.binding.imgProduct)

        holder.itemView.setOnClickListener {
            onClick(item)
        }

        holder.binding.btnRemove.setOnClickListener {
            removeItem(position)
        }
    }

    override fun getItemCount() = items.size

    fun removeItem(position: Int){
        val items = CartManager.getItems();
        val item = items[position]
        onRemove(item);
    }


}