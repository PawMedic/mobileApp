//package com.example.paymentpawmedic.presentation.produk
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//
//import androidx.compose.ui.text.style.TextOverflow
//// Data class untuk merepresentasikan barang
//data class Product(val name: String, val price: String, val imageResId: Int)
//
//// Daftar barang
//val productList = listOf(
//    Product("Produk 1", "Rp 100.000", R.drawable.product1),
//    Product("Produk 2", "Rp 200.000", R.drawable.product2),
//    Product("Produk 3", "Rp 300.000", R.drawable.product3),
//    // Tambahkan produk lainnya di sini
//)
//
//@Composable
//fun ProductList() {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(productList) { product ->
//            ProductItem(product)
//        }
//    }
//}
//
//@Composable
//fun ProductItem(product: Product) {
//    val typography = MaterialTheme.typography
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = product.imageResId),
//            contentDescription = null,
//            modifier = Modifier
//                .size(80.dp)
//                .padding(end = 16.dp)
//        )
//        Column {
//            Text(
//                text = product.name,
//                style = typography.subtitle1,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//            Text(
//                text = product.price,
//                style = typography.body1
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewProductList() {
//    ProductList()
//}
