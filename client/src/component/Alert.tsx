import { FC } from "react";
import { useEffect } from "react";
import { connect } from "react-redux";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import { StoreState } from "../reducers";
import { alertMessage } from "../actions/";
import { alert } from "../ultis/toast";
import { clear } from "../actions/alert";

interface Props {
  alertOption: alertMessage;
  clear: () => void;
}

const Alert: FC<Props> = ({ alertOption, clear }) => {
  useEffect(() => {
    if (alertOption.message !== "") {
      alert(alertOption);
      clear();
    }
  }, [alertOption, clear]);
  return (
    <div>
      <ToastContainer />
    </div>
  );
};

const mapStateToProps = (state: StoreState) => {
  return { alertOption: state.AlertReducer };
};

export default connect(mapStateToProps, { clear })(Alert);
