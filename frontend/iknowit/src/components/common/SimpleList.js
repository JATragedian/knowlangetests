import React from "react"

class SimpleList extends React.Component {

    render() {
        return (
            <ul>
                {this.props.objects.map(object => (
                    this.props.renderListedObject(object)
                ))}
            </ul>
        )
    }
}

export default SimpleList