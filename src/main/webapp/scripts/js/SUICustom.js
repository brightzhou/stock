mini.plugin(mini.FileUpload, {
   __on_file_queued_error: function(file, errorCode, message){
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:

                mini.alert("上传文件不能超过 "+ this.limitSize);
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:

                mini.alert("文件大小为0, 文件名: " + file.name);
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:

                mini.alert("文件类型不符, 文件名: " + file.name);
                break;
            default:

                break;
        }
    },
    __OnMouseMove: function (evt) {
        if (this.enabled == false) return;

        var sf = this;
       
        if (!this.swfUpload) {
            //校验是否安装flash player 赵美丹 2013-05-06
            if(!this.flashChecker().hasFlash){
               mini.alert("未安装Flash Player或者版本过低,请点击确定安装,安装完成之后请重启浏览器.","确定",function(action){
            	   window.open("http://get.adobe.com/cn/flashplayer/");
               });
               return;
            }
            
            var upload = new SWFUpload({
                file_post_name: this.name,
                upload_url: sf.uploadUrl,
                flash_url: sf.flashUrl,

                
                file_size_limit: sf.limitSize,  
                file_types: sf.limitType,   
                file_types_description: sf.typesDescription,
                file_upload_limit: parseInt(sf.uploadLimit),
                file_queue_limit: sf.queueLimit,
                use_query_string : sf.usequerystring,
                
                file_queued_handler: mini.createDelegate(this.__on_file_queued, this),
                file_queue_error_handler: mini.createDelegate(this.__on_file_queued_error, this),
                upload_error_handler: mini.createDelegate(this.__on_upload_error, this),
                upload_success_handler: mini.createDelegate(this.__on_upload_success, this),
                upload_complete_handler: mini.createDelegate(this.__on_upload_complete, this),
                upload_progress_handler: mini.createDelegate(this.__on_upload_progress, this),

                
                button_placeholder_id: this._uploadId,
                button_width: 1000,
                button_height: 50,
                button_window_mode: "transparent",
                button_action : SWFUpload.BUTTON_ACTION.SELECT_FILE,

                
                debug: false

            });
            upload.flashReady();
            this.swfUpload = upload;

            var el = this.swfUpload.movieElement;
            el.style.zIndex = 1000;
            el.style.position = "absolute";
            el.style.left = "0px";
            el.style.top = "0px";
            el.style.width = "100%";
            el.style.height = "50px";


        } else {     
            
        }
    }
});

mini.plugin(mini.AutoComplete, {
	__OnInputKeyDown: function (e) {
	    this.fire("keydown", { htmlEvent: e });
        //BackSpace
	    if (e.keyCode == 8 && (this.isReadOnly() || this.allowInput == false)) {
	        return false;
	    }
        //Tab
	    if (e.keyCode == 9) {
	        this.hidePopup();
	        return;
	    }

	    if (this.isReadOnly()) return;

	    switch (e.keyCode) {
	        case 27:      //Escape
	            if (this.isShowPopup()) {
	                e.stopPropagation();
	            }

	            this.hidePopup();
	            break;
	        case 13:       //Enter
	            if (this.isShowPopup()) {
	                e.preventDefault();
	                e.stopPropagation();

	                var index = this._listbox.getFocusedIndex();

	                if (index != -1) {
	                    var item = this._listbox.getAt(index);
	                    var vts = this._listbox.getValueAndText([item]);
	                    var value = vts[0];

	                    this.setText(vts[1]);
	                    if (mini.isFirefox) {
	                        this.blur();
	                        this.focus();
	                    }

	                    this.setValue(value, false);

	                    this.hidePopup();

	                }
	            } else {
	                this.fire("enter");
	            }
	            break;
	        case 37:
	            break;
	        case 38:     //UP
	            var index = this._listbox.getFocusedIndex();
	            if (index == -1) {
	                index = 0;
	                if (!this.multiSelect) {
	                    var item = this._listbox.findItems(this.value)[0];
	                    if (item) {
	                        index = this._listbox.indexOf(item);
	                    }
	                }
	            }
	            if (this.isShowPopup()) {
	                if (!this.multiSelect) {
	                    index -= 1;
	                    if (index < 0) index = 0;
	                    this._listbox._focusItem(index, true);
	                }
	            }
	            break;
	        case 39:
	            break;
	        case 40:   //DOWN
	        	
	            var index = this._listbox.getFocusedIndex();
	            if (this.isShowPopup()) {
	                if (!this.multiSelect) {
	                    index += 1;
	                    if (index > this._listbox.getCount() - 1) index = this._listbox.getCount() - 1;
	                    this._listbox._focusItem(index, true);
	                }
	            } else {
	                //this._tryQuery(this._textEl.value);
	            }
	            break;
	        default:
	            //this._tryQuery(this._textEl.value);
	            break;
	    }
	},
	_OnValueChanged: function () {
		if (this.validateOnChanged) {
			this.validate();
		}
		var value = this.getValue();
		var selecteds = this.getSelecteds();
		var selected = selecteds[0];
		var sf = this;

		sf.fire("valuechanged", {
			value: value,
			selecteds: selecteds,
			selected: selected
		});
		isClick = 0;

	},
    __OnBlur: function (e) {
        this._focused = false;
        var sf = this;
        setTimeout(function () {
            if (sf._focused == false) {
                sf.removeCls(sf._focusCls);
            }
        }, 2);
       
        this.fire("blur", {
            htmlEvent: e
        });
        if (this.validateOnLeave) {
            this._tryValidate();
        }
        if(this._textEl.value && !isClick){
			this._tryQuery(this._textEl.value);
			this.select(1);
		}
        isClick = 0;
    }
	});