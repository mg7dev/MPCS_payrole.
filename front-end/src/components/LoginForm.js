import React from 'react';
import { Container, Row, Col, Button,Input,Form,FormGroup } from 'reactstrap';

const LoginForm = (props) => {
  return (
    <Container style={{paddingTop:'30vh',background:'#102454'}}>
      <Row style={{textAlign:'center'}}>
        <Col>
            <h2>MPCS PAY ROLE</h2>
            <h4>we care your salaries.</h4>
            <Button>Click here!</Button>
        </Col>
        <Col>
            <Form>
                <h3>Please sign in</h3>
                <FormGroup>
                    <Input type="email" name="email"  placeholder="username" />
                </FormGroup>
                <FormGroup>
                    <Input type="password" name="password"  placeholder="password" />
                </FormGroup>
                <FormGroup>
                    <Button>login</Button>
                </FormGroup>
            </Form>
        </Col>
      </Row>
     
    </Container>
  );
}

export default LoginForm;