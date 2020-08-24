import React, { useState } from 'react';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText
} from 'reactstrap';
import { Link } from 'react-router-dom';

const Example = (props) => {
    const list = props.item.map((item,index)=>{
        return (

        <NavItem key={index}>
            <NavLink><Link to={item.link}>{item.text}</Link></NavLink>
        </NavItem>
        )
    })
    return (
        <div>
        <Navbar color="light" light expand="md">
            {/* <NavbarBrand href="/">reactstrap</NavbarBrand> */}
            <Nav className="mr-auto" navbar style={{margin:'auto'}}>
                {list}
            </Nav>
            <NavbarText>Logout</NavbarText>
        </Navbar>
        </div>
    );
}

export default Example;