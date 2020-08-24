import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {  BrowserRouter as Router, Switch,Route } from 'react-router-dom';

import TopNavbar from './components/TopNavbar';
import LoginForm from './components/LoginForm';
import Home from './components/Home';
import AddEmployee from './components/AddEmp';
import ViewEmployee from './components/ViewEmp';
function App() {
	const item = [
		{link:'/',text:'Home'},
		{link:'/login',text:'login'},
		{link:'/addEmployee',text:'addEmployee'},
		{link:'/viewemp',text:'View Employee'},
	]
  return (
	<Router>
		<div>
			<TopNavbar item={item} />
			<Switch>
			<Route path="/addEmployee">
					<AddEmployee />
				</Route>
				<Route path="/viewemp">
					<ViewEmployee />
				</Route>
				<Route path="/login">
					<LoginForm />
				</Route>
				<Route path="/">
					<Home />
				</Route>
			</Switch>
		</div>
	</Router>
  );
}

export default App;
