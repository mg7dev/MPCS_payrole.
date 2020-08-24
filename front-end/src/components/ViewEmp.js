import React from 'react';
import { Container, Row, Col, Button,Input,Form,FormGroup ,Label, TabContent, Table} from 'reactstrap';

const AddEmp = (props) => {
  return (
    <Container style={{paddingTop:'10px',background:'#102454'}}>
        <Row>
            <Col lg={12} style={{display:"flex"}}>
                <Button type="button" size='' style={{margin:'auto',display:"flex"}}>Download</Button>
                <Input type="text" placeholder="search" style={{width:'200px',float:'right',display:"flex"}} />
            </Col>
        </Row>
        <Row style={{paddingTop:'10px'}}>
            <Table dark>
                <thead>
                  <tr>
                    <td>Name</td>
                    <td>Employee Id</td>
                    <td>Basic Salary</td>
                    <td>Allowances</td>
                    <td>Deductions</td>
                  </tr>
                </thead>
                <tbody>
                </tbody>
            </Table>
        </Row>
    </Container>
  );
}

export default AddEmp;