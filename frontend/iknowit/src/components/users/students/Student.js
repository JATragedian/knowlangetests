import React from "react"
import {Link} from 'react-router-dom';
import styles from "../User.module.css"
import {Button, ButtonGroup} from "reactstrap";

class Student extends React.Component {

    render() {
        return (
            <tr key={this.props.user.id}>
                <td>
                    <input
                        type="checkbox"
                        className={styles.checkbox}
                        onChange={() => this.props.handleSelect(this.props.user.id)}/>
                </td>
                <td style={{whiteSpace: 'nowrap'}}>{this.props.user.name + ' ' + this.props.user.surname}</td>
                <td>{this.props.user.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="secondary" tag={Link} to={"/user/" + this.props.user.id}>View</Button>
                    </ButtonGroup>
                </td>
            </tr>
        )
    }
}

export default Student