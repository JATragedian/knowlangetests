import React from "react"
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";

class Exam extends React.Component {

    getMinutes(seconds) {
        return Math.floor(seconds / 60);
    }

    render() {
        return (
            <tr key={this.props.exam.id}>
                <td style={{whiteSpace: 'nowrap'}}>{this.props.exam.name}</td>
                <td>{this.props.exam.description}</td>
                <td>{this.getMinutes(this.props.exam.timeLimit)} мин</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="secondary" tag={Link} to={"/user/" + this.props.exam.id}>Открыть</Button>
                    </ButtonGroup>
                </td>
            </tr>
        )
    }

}

export default Exam