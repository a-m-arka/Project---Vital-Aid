import React, { useState, useEffect } from 'react'
import '../style/OrderList.scss'

const OrderList = () => {

  const [orderList, setOrderList] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchOrders = async () => {
      const token = localStorage.getItem('token');
      const url = 'http://localhost:8080/vital_aid/orderProduct/userOrders';

      try {
        const response = await fetch(url, {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.ok) {
          const data = await response.json();
          setOrderList(data);
          // console.log(data);
        } else {
          const errorMessage = await response.text();
          console.log(errorMessage);
        }
      } catch (err) {
        console.error('Error fetching order data:', err.message);
      }
    };

    fetchOrders();
  }, []);

  const handleCancelOrder = async (id) => {
    const token = localStorage.getItem('token');
    const url = `http://localhost:8080/vital_aid/orderProduct/deleteOrder/${id}`;
    setLoading(true);
    setError('');

    try {
      const response = await fetch(url, {
        method: 'DELETE',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.ok) {
        window.location.reload();
      } else {
        const errorMessage = await response.text();
        console.log(errorMessage);
        setError(errorMessage || 'Failed to cancel order.');
      }
    } catch (err) {
      console.error('Error fetching order data:', err.message);
      setError(err.message || 'Failed to cancel order.')
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className='rout-container'>
      <div className="orders-container">
        <div className="heading caption">
          <h1>Your Orders</h1>
        </div>
        {orderList.length !== 0 ? (
          <div className="order-list">
            {orderList.map((order) => (
              <div className="order" key={order.id}>
                <div className="left">
                  <img src={order.orderedProductPhotoUrl} alt="" />
                </div>
                <div className="right">
                  <div className="top">
                    <div className="details">
                      <div className="attribute">Product</div>
                      <div className="colon">:</div>
                      <div className="attribute-value">{order.orderMadeFor}</div>
                    </div>
                    <div className="details">
                      <div className="attribute">Category</div>
                      <div className="colon">:</div>
                      <div className="attribute-value">{order.orderedProductCategory}</div>
                    </div>
                    <div className="details">
                      <div className="attribute">Quantity</div>
                      <div className="colon">:</div>
                      <div className="attribute-value">{order.quantity}</div>
                    </div>
                    <div className="details">
                      <div className="attribute">Total Price</div>
                      <div className="colon">:</div>
                      <div className="attribute-value">BDT {order.totalPrice}</div>
                    </div>
                    <div className="details">
                      <div className="attribute">Delivery Address</div>
                      <div className="colon">:</div>
                      <div className="attribute-value">{order.location}</div>
                    </div>
                  </div>
                  {error && (
                    <div className="message" style={{color: 'red'}}><p>{error}</p></div>
                  )}
                  <div className="bottom">
                    {loading ? (
                      <p className="loading-message">Canceling Order...</p>
                    ) : (
                      <div className="cancel-order" onClick={() => handleCancelOrder(order.id)}>Cancel Order</div>
                    )}
                  </div>
                </div>
              </div>
            ))}
          </div>
        ) : (
          <h3>You haven't placed any orders yet. Start shopping now!</h3>
        )}
      </div>
    </div>
  )
}

export default OrderList
