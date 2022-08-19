function finalPrice() {
    /*<![CDATA[*/
    let price = parseInt(document.getElementById("price").innerHTML, 10);
    let quantity = parseInt(document.getElementById("quantity").innerHTML, 10);
    let finalPrice = price * quantity;

    document.getElementById("totalPrice").innerHTML(finalPrice.toString());
    /*]]>*/
}