import React from "react"
import Header from "../../header/Header";
import Users from "../Users";
import {Button, ButtonGroup, Table} from "reactstrap";
import Student from "./Student";

class Students extends Users {

    getFetchUrl() {
        return '/api/v1/users?page=' + this.state.page + "&size=" + this.state.size + "&role=STUDENT";
    }

    renderUserInfo(user) {
        return (
            <user-info>
                {user.name} {user.surname} {user.email}
            </user-info>
        )
    }

    render() {
        const userList = this.state.users.map(user => (
            <Student
                key={user.id}
                user={user}
                handleSelect={this.handleSelect}
            />
        ));

        return (
            <>
                <Header title={"Студенты"}/>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="5%"/>
                        <th width="25%">Имя</th>
                        <th width="25%">Email</th>
                        <th width="20%"/>
                    </tr>
                    </thead>
                    <tbody>
                    {userList}
                    </tbody>
                </Table>
                <ButtonGroup>
                    <Button size="sm" color="secondary">Add to group</Button>
                </ButtonGroup>
            </>
        )
    }
}

export default Students