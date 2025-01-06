// import { useState, useEffect } from 'react';
import Home from './pages/home/Home';
import Login from './pages/login/Login';
import Signup from './pages/signup/Signup';
import List from './pages/list/List';
import New from './pages/new/New';
import Single from './pages/single/Single';
import Update from './pages/update/Update';
import Profile from './pages/profile/Profile';
import ForgetPass from './pages/forgetPass/ForgetPass';
import { userColumns } from './datasource/userdata';
import { doctorColumns } from './datasource/doctorData';
import { hospitalColumns } from './datasource/hospitalData';
import { productColumns } from './datasource/productData';
import { ambulanceColumns } from './datasource/ambulanceData';
import PrivateRoute from './components/privateRoute/PrivateRoute';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate
} from 'react-router-dom';



function App() {

  return (
    <div className="App">

      <Router>

        <Routes>
          <Route path='/'>
            <Route index element={<Navigate to="/login" />}></Route>
            <Route path='login' element={<Login />}></Route>
            <Route path='forgetpassword' element={<ForgetPass />}></Route>
            <Route path='signup' element={<Signup />}></Route>
            <Route element={<PrivateRoute />}>
              <Route path='home' element={<Home />}></Route>
            </Route>
            <Route element={<PrivateRoute />}>
              <Route path='profile' element={<Profile />}></Route>
            </Route>
            <Route element={<PrivateRoute />}>
              <Route path='user'>
                <Route index element={<List type="user" columns={userColumns} adminControl={false} />}></Route>
                <Route path=':userId' element={<Single type="user" adminControl={false} />}></Route>
              </Route>
            </Route>
            <Route element={<PrivateRoute />}>
              <Route path='doctor'>
                <Route index element={<List type="doctor" columns={doctorColumns} adminControl={true} />}></Route>
                <Route path=':doctorId'>
                  <Route index element={<Single type="doctor" adminControl={true} />}></Route>
                  <Route path='update' element={<Update type="doctor" />}></Route>
                </Route>
                <Route path='new' element={<New type="doctor" />}></Route>
              </Route>
            </Route>
            <Route element={<PrivateRoute />}>
              <Route path='hospital'>
                <Route index element={<List type="hospital" columns={hospitalColumns} adminControl={true} />}></Route>
                <Route path=':hospitalId'>
                  <Route index element={<Single type="hospital" adminControl={true} />}></Route>
                  <Route path='update' element={<Update type="hospital" />}></Route>
                </Route>
                <Route path='new' element={<New type="hospital" />}></Route>
              </Route>
            </Route>
            <Route element={<PrivateRoute />}>
              <Route path='ambulance'>
                <Route index element={<List type="ambulance" columns={ambulanceColumns} adminControl={true} />}></Route>
                <Route path=':ambulanceId'>
                  <Route index element={<Single type="ambulance" adminControl={true} />}></Route>
                  <Route path='update' element={<Update type="ambulance" />}></Route>
                </Route>
                <Route path='new' element={<New type="ambulance" />}></Route>
              </Route>
            </Route>
            <Route element={<PrivateRoute />}>
              <Route path='product'>
                <Route index element={<List type="product" columns={productColumns} adminControl={true} />}></Route>
                <Route path=':productId'>
                  <Route index element={<Single type="product" adminControl={true} />}></Route>
                  <Route path='update' element={<Update type="product" />}></Route>
                </Route>
                <Route path='new' element={<New type="product" />}></Route>
              </Route>
            </Route>
          </Route>
        </Routes>

      </Router>

    </div>
  );
}

export default App;
